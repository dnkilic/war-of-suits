package com.dnkilic.warofsuits.player.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dnkilic.warofsuits.player.model.InputValidation
import com.dnkilic.warofsuits.player.model.ValidateException
import com.dnkilic.warofsuits.player.repository.PlayerRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlayerViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<PlayerRepository>(relaxed = true)
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Test
    fun `attempt to start game twice while first request is in progress`() {
        val playerName = "Dogan Kilic"
        coEvery { repository.validate(any()) } returns flow {
            delay(1)
            emit(InputValidation(playerName))
        }

        val viewModel = PlayerViewModel(repository, coroutineDispatcher)
        viewModel.startNewGame(playerName)
        viewModel.startNewGame(playerName)

        verify(exactly = 1) { repository.validate(any()) }
    }

    @Test
    fun `successfully start new game`() {
        val playerName = "Dogan Kilic"
        coEvery { repository.validate(any()) } returns flow { emit(InputValidation(playerName)) }

        val viewModel = PlayerViewModel(repository, coroutineDispatcher)
        viewModel.startNewGame(playerName)

        assertTrue(viewModel.uiState.value.playerName == playerName)
        assertTrue(!viewModel.uiState.value.loading)
    }

    @Test
    fun `input validation error while starting new game`() {
        coEvery { repository.validate(any()) } returns flow { throw ValidateException(1) }

        val viewModel = PlayerViewModel(repository, coroutineDispatcher)
        viewModel.startNewGame("foo")

        assertTrue(viewModel.uiState.value.errorResId == 1)
        assertTrue(!viewModel.uiState.value.loading)
    }

    @Test
    fun `successfully reset ui state while start game request is in progress`() {
        val playerName = "Dogan Kilic"
        coEvery { repository.validate(any()) } returns flow {
            delay(1)
            emit(InputValidation(playerName))
        }

        val viewModel = PlayerViewModel(repository, coroutineDispatcher)
        viewModel.startNewGame(playerName)
        viewModel.resetUiState()

        assertNull(viewModel.uiState.value.playerName)
        assertTrue(!viewModel.uiState.value.loading)
        assertNull(viewModel.uiState.value.errorResId)
    }
}