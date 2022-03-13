package com.dnkilic.warofsuits.game.model

data class GameUiState(
    val splitCards: Boolean = false,
    val playerPoints: Int = 0,
    val opponentPoints: Int = 0,
)