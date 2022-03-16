package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.components.ActionText
import com.dnkilic.uicomponents.components.PlayerLabel
import com.dnkilic.uicomponents.theme.AppTheme
import com.dnkilic.warofsuits.game.R
import com.dnkilic.warofsuits.game.model.GameState

@Composable
fun Footer(
    modifier: Modifier = Modifier,
    playerName: String,
    gameState: GameState?,
    tintColor: Color,
    onFooterAction: (FooterAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerLabel(playerName = playerName, textColor = tintColor)
        if (gameState != null) {
            FooterAction(
                gameState = gameState,
                onFooterAction = onFooterAction
            )
            Spacer(modifier = Modifier.height(AppTheme.spaces.S))
        }
    }
}

@Composable
private fun FooterAction(
    gameState: GameState,
    onFooterAction: (FooterAction) -> Unit,
) {
    when (gameState) {
        GameState.INITIAL -> ActionText(
            text = stringResource(id = R.string.click_to_start),
            onClick = { onFooterAction(FooterAction.START) },
        )
        GameState.SHUFFLED, GameState.IDLE -> ActionText(
            text = stringResource(id = R.string.click_to_play),
            onClick = { onFooterAction(FooterAction.PLAY) },
        )
        GameState.OVER -> ActionText(
            text = stringResource(id = R.string.click_to_play_again),
            onClick = { onFooterAction(FooterAction.RESET) },
        )
    }
}

@Preview
@Composable
private fun FooterPreview() {
    Column {
        Footer(
            playerName = "PlayerX",
            tintColor = Color.White,
            gameState = GameState.IDLE,
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.S))
        Footer(
            playerName = "PlayerX",
            tintColor = Color.White,
            gameState = GameState.OVER,
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.S))
        Footer(
            playerName = "PlayerX",
            tintColor = Color.White,
            gameState = GameState.SHUFFLED,
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.S))
        Footer(
            playerName = "PlayerX",
            tintColor = Color.White,
            gameState = GameState.INITIAL,
        )
    }

}

enum class FooterAction {
    START, RESET, PLAY
}