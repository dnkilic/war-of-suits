package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.components.CardDeck

@Composable
fun GameScreen(
    userName: String,
    gameViewModel: IGameViewModel
) {
    Scaffold(
        content = {
            GameScreenContent(
                userName = userName,
                gameViewModel = gameViewModel
            )
        }
    )
}

@Composable
private fun GameScreenContent(
    userName: String,
    gameViewModel: IGameViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background))
    {
        CardDeck()
    }
}

@Composable
@Preview
private fun GameScreenPreview() {
    GameScreen(
        userName = "PlayerX",
        gameViewModel = PreviewGameViewModel()
    )
}

private class PreviewGameViewModel : IGameViewModel()