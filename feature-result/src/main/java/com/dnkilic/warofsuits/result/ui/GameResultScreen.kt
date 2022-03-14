package com.dnkilic.warofsuits.result.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.components.PrimaryButton
import com.dnkilic.uicomponents.theme.AppTheme
import com.dnkilic.warofsuits.result.R
import com.dnkilic.warofsuits.result.model.GameResult
import com.dnkilic.warofsuits.result.model.GameResultUiState
import com.dnkilic.warofsuits.result.model.gameResult

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameResultScreen(
    uiState: GameResultUiState,
    onPlayAgain: () -> Unit = {},
) {
    val title = when (uiState.gameResult()) {
        GameResult.PLAYER_WON -> stringResource(id = R.string.player_won, uiState.playerName)
        GameResult.DRAW -> stringResource(id = R.string.draw)
        GameResult.OPPONENT_WON -> stringResource(id = R.string.player_lose, uiState.playerName)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.spaces.M),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = title)
        Spacer(modifier = Modifier.height(AppTheme.spaces.M))
        Score(playerScore = uiState.playerScore, opponentScore = uiState.opponentScore)
        Spacer(modifier = Modifier.height(AppTheme.spaces.M))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.play_again),
            onClick = onPlayAgain
        )
    }
}

@Composable
private fun Title(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun Score(
    playerScore: Int,
    opponentScore: Int
) {
    Text(
        text = stringResource(id = R.string.match_score, playerScore, opponentScore),
        style = MaterialTheme.typography.body1
    )
}

@Preview
@Composable
fun GameResultScreenPreview() {
    GameResultScreen(
        GameResultUiState(playerScore = 10, opponentScore = 12, "dnkilic")
    )
}