package com.dnkilic.warofsuits.result.model

data class GameResultUiState(
    val playerScore: Int,
    val opponentScore: Int,
    val playerName: String
)

fun GameResultUiState.gameResult(): GameResult {
    return when {
        this.playerScore > this.opponentScore -> {
            GameResult.PLAYER_WON
        }
        this.playerScore == this.opponentScore -> {
            GameResult.DRAW
        }
        else -> {
            GameResult.OPPONENT_WON
        }
    }
}

enum class GameResult {
    PLAYER_WON, DRAW, OPPONENT_WON
}