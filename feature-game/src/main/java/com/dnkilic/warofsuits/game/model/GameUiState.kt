package com.dnkilic.warofsuits.game.model

import com.dnkilic.uicomponents.model.CardDto
import com.dnkilic.uicomponents.model.CardType

data class GameUiState(
    val playerPoints: Int = 0,
    val opponentPoints: Int = 0,
    val cards: List<CardDto> = emptyList(),
    val loading: Boolean = false,
    val playing: Boolean = false,
    val gameState: GameState? = null,
    val suitPriority: List<CardType> = emptyList()
)

enum class GameState {
    INITIAL, SHUFFLED, IDLE
}