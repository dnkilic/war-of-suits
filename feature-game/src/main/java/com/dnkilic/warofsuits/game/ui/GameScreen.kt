package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.components.CardDeck
import com.dnkilic.warofsuits.game.model.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GameScreen(
    userName: String,
    gameViewModel: IGameViewModel
) {
    val uiState by gameViewModel.uiState.collectAsState()

    Scaffold(
        content = {
            GameScreenContent(
                userName = userName,
                uiState = uiState
            )
        }
    )
}

@Composable
private fun GameScreenContent(
    userName: String,
    uiState: GameUiState
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

private class PreviewGameViewModel : IGameViewModel() {
    override val uiState: StateFlow<GameUiState> = MutableStateFlow(GameUiState())
}