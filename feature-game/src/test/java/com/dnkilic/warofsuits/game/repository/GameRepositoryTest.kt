package com.dnkilic.warofsuits.game.repository

import com.dnkilic.warofsuits.data.model.CardDto
import com.dnkilic.warofsuits.data.model.CardType
import com.dnkilic.warofsuits.game.model.Gamer
import com.dnkilic.warofsuits.game.utils.*
import org.junit.Assert
import org.junit.Test

class GameRepositoryTest {

    private val gameRepository = GameRepository()

    @Test
    fun `game finishes as player wins`() {
        val (playerScore, opponentScore) = gameSimulation(cardDeckPlayerWins(), fakeSuitPriority())
        Assert.assertTrue(playerScore > opponentScore)
    }

    @Test
    fun `game finishes as opponent wins`() {
        val (playerScore, opponentScore) = gameSimulation(cardDeckOpponentWins(), fakeSuitPriority())
        Assert.assertTrue(opponentScore > playerScore)
    }

    @Test
    fun `game finishes as player wins with suit priority`() {
        val (playerScore, opponentScore) = gameSimulation(cardDeckDraw(), cardSuitPlayerWins())
        Assert.assertTrue(playerScore > opponentScore)
    }

    @Test
    fun `game finishes as opponent wins with suit priority`() {
        val (playerScore, opponentScore) = gameSimulation(cardDeckDraw(), cardSuitOpponentWins())
        Assert.assertTrue(opponentScore > playerScore)
    }

    @Test
    fun `game finishes as draw`() {
        val (playerScore, opponentScore) = gameSimulation(cardDeckDraw(), cardSuitDraw())
        Assert.assertTrue(opponentScore == playerScore)
    }

    private fun gameSimulation(cardDeck: MutableList<CardDto>, suitPriority: List<CardType>): Pair<Int, Int> {
        var playerScore = 0
        var opponentScore = 0
        when (gameRepository.getRoundWinner(cardDeck.last(), cardDeck[2], suitPriority)) {
            Gamer.Player -> playerScore++
            Gamer.Opponent -> opponentScore++
        }
        val updatedCardDeck = cardDeck.dropLast(2)
        when (gameRepository.getRoundWinner(updatedCardDeck.last(), updatedCardDeck[0], suitPriority)) {
            Gamer.Player -> playerScore++
            Gamer.Opponent -> opponentScore++
        }

        return playerScore to opponentScore
    }
}