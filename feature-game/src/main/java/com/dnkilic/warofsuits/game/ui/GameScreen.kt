package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.dnkilic.warofsuits.game.model.GameState
import com.dnkilic.warofsuits.game.model.GameUiState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GameScreen(
    playerName: String,
    gameViewModel: IGameViewModel,
    onStartGame: () -> Unit = {},
    onPlayCard: () -> Unit = {},
    onEndGame: (Int, Int) -> Unit = { _: Int, _: Int -> },
    onResetGame: () -> Unit = {},
) {
    val uiState by gameViewModel.uiState.collectAsState()

    Scaffold(
        content = {
            GameScreenContent(
                playerName = playerName,
                uiState = uiState,
                onStartGame = onStartGame,
                onPlayCard = onPlayCard,
                onEndGame = onEndGame,
                onResetGame = onResetGame
            )
        }
    )
}

@Composable
private fun GameScreenContent(
    playerName: String,
    uiState: GameUiState,
    onStartGame: () -> Unit,
    onPlayCard: () -> Unit,
    onEndGame: (Int, Int) -> Unit,
    onResetGame: () -> Unit,
) {
    if (uiState.gameState == GameState.OVER) {
        onEndGame(uiState.playerPoints, uiState.opponentPoints)
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.primary,
        darkIcons = false
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (header, board, score, footer) = createRefs()

        Header(modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(board.top)
            },
            tintColor = Color.White,
            onResetGame = onResetGame
        )

        Board(modifier = Modifier
            .background(MaterialTheme.colors.secondaryVariant)
            .constrainAs(board) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                end.linkTo(score.start)
                bottom.linkTo(footer.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            uiState = uiState,
        )

        Score(modifier = Modifier
            .background(MaterialTheme.colors.secondary)
            .constrainAs(score) {
                top.linkTo(header.bottom)
                start.linkTo(board.end)
                end.linkTo(parent.end)
                bottom.linkTo(footer.top)
                height = Dimension.fillToConstraints
            },
            opponentScore = uiState.opponentPoints,
            playerScore = uiState.playerPoints,
            textColor = Color.Black,
            priorityList = uiState.suitPriority
        )

        Footer(modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .constrainAs(footer) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(board.bottom)
            },
            tintColor = Color.White,
            playerName = playerName,
            gameState = uiState.gameState,
            onFooterAction = {
                when (it) {
                    FooterAction.START -> onStartGame()
                    FooterAction.RESET -> onResetGame()
                    FooterAction.PLAY -> onPlayCard()
                }
            }
        )
    }
}

@Composable
@Preview
private fun GameScreenPreview() {
    GameScreen(
        playerName = "PlayerX",
        gameViewModel = PreviewGameViewModel()
    )
}

private class PreviewGameViewModel : IGameViewModel() {
    override val uiState: StateFlow<GameUiState> = MutableStateFlow(GameUiState())
}