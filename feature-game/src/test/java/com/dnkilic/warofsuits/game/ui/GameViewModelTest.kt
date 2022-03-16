package com.dnkilic.warofsuits.game.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dnkilic.warofsuits.data.model.CardState
import com.dnkilic.warofsuits.game.model.GameState
import com.dnkilic.warofsuits.game.model.Gamer
import com.dnkilic.warofsuits.game.repository.GameRepository
import com.dnkilic.warofsuits.game.utils.fakeCard
import com.dnkilic.warofsuits.game.utils.fakeDeck
import com.dnkilic.warofsuits.game.utils.fakeSuitPriority
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<GameRepository>(relaxed = true)
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Test
    fun `successfully play next card`() = runBlocking {
        every { repository.getDeck() } returns fakeDeck()
        every { repository.getSuitPriority() } returns fakeSuitPriority()

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(6000)

        verify(exactly = 1) { repository.getRoundWinner(any(), any(), any()) }
    }

    @Test
    fun `attempt to play multiple cards while first round is in progress`() = runBlocking {
        every { repository.getDeck() } returns fakeDeck()
        every { repository.getSuitPriority() } returns fakeSuitPriority()

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        viewModel.playNextCard()
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(20000)

        verify(exactly = 1) { repository.getRoundWinner(any(), any(), any()) }
    }

    @Test
    fun `card statuses updated between 2 seconds intervals`() = runBlocking {
        every { repository.getDeck() } returns listOf(fakeCard(), fakeCard())
        every { repository.getSuitPriority() } returns fakeSuitPriority()

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        viewModel.playNextCard()
        Assert.assertTrue(viewModel.uiState.value.cards.last().cardState == CardState.PLAYED_BY_PLAYER)
        coroutineDispatcher.advanceTimeBy(2000)
        Assert.assertTrue(viewModel.uiState.value.cards.last().cardState == CardState.REVEALED_BY_PLAYER)
        coroutineDispatcher.advanceTimeBy(2000)

        verify(exactly = 1) { repository.getRoundWinner(any(), any(), any()) }
    }

    @Test
    fun `game is over when last two cards are played`() = runBlocking {
        every { repository.getDeck() } returns listOf(fakeCard(), fakeCard())
        every { repository.getSuitPriority() } returns fakeSuitPriority()

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(6000)

        verify(exactly = 1) { repository.getRoundWinner(any(), any(), any()) }
        Assert.assertTrue(viewModel.uiState.value.gameState == GameState.OVER)
    }

    @Test
    fun `game finishes as player wins`() = runBlocking {
        every { repository.getDeck() } returns fakeDeck()
        every { repository.getSuitPriority() } returns fakeSuitPriority()
        every { repository.getRoundWinner(any(), any(), any()) } returns Gamer.Player

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(4000)
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(4000)

        Assert.assertTrue(viewModel.uiState.value.gameState == GameState.OVER)
        Assert.assertTrue(viewModel.uiState.value.playerPoints > viewModel.uiState.value.opponentPoints)
    }

    @Test
    fun `game finishes as opponent wins`() = runBlocking {
        every { repository.getDeck() } returns fakeDeck()
        every { repository.getSuitPriority() } returns fakeSuitPriority()
        every { repository.getRoundWinner(any(), any(), any()) } returns Gamer.Opponent

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(4000)
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(4000)

        Assert.assertTrue(viewModel.uiState.value.gameState == GameState.OVER)
        Assert.assertTrue(viewModel.uiState.value.playerPoints < viewModel.uiState.value.opponentPoints)
    }

    @Test
    fun `game finishes as draw`() = runBlocking {
        every { repository.getDeck() } returns fakeDeck()
        every { repository.getSuitPriority() } returns fakeSuitPriority()

        val viewModel = GameViewModel(repository, coroutineDispatcher)
        every { repository.getRoundWinner(any(), any(), any()) } returns Gamer.Opponent
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(4000)
        every { repository.getRoundWinner(any(), any(), any()) } returns Gamer.Player
        viewModel.playNextCard()
        coroutineDispatcher.advanceTimeBy(4000)

        Assert.assertTrue(viewModel.uiState.value.gameState == GameState.OVER)
        Assert.assertTrue(viewModel.uiState.value.playerPoints == viewModel.uiState.value.opponentPoints)
    }
}