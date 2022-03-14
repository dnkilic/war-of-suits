package com.dnkilic.warofsuits.game.ui

import android.animation.TimeInterpolator
import android.view.animation.AnticipateInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dnkilic.uicomponents.components.ActionText
import com.dnkilic.uicomponents.components.LabelOrientation
import com.dnkilic.uicomponents.components.PlayCard
import com.dnkilic.uicomponents.components.PlayerLabel
import com.dnkilic.uicomponents.model.CardState
import com.dnkilic.warofsuits.game.R
import com.dnkilic.warofsuits.game.model.GameState
import com.dnkilic.warofsuits.game.model.GameUiState

@Composable
fun Board(
    modifier: Modifier = Modifier,
    uiState: GameUiState,
    userName: String,
    onStartGame: () -> Unit = {},
    onPlayCard: () -> Unit = {},
    onResetGame: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val heightOfCard = 220
    val distanceToCenter = heightOfCard / 2
    val screenHeight = (configuration.screenHeightDp / 2) - distanceToCenter

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (header, footer, score, playArea) = createRefs()

        Header(
            modifier = Modifier
                .background(Color.LightGray)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Score(
            playerScore = uiState.playerPoints,
            opponentScore = uiState.opponentPoints,
            priorityList = uiState.suitPriority,
            modifier = Modifier
                .background(Color.LightGray)
                .constrainAs(score) {
                    top.linkTo(header.top)
                    bottom.linkTo(footer.bottom)
                    end.linkTo(parent.end)
                },
            onResetGame = onResetGame
        )

        Footer(
            modifier = Modifier
                .background(Color.LightGray)
                .constrainAs(footer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            userName = userName
        ) {
            if (uiState.gameState != null) {
                when (uiState.gameState) {
                    GameState.INITIAL -> ActionText(
                        text = stringResource(id = R.string.click_to_start),
                        onClick = {
                            if (!uiState.playing) onStartGame()
                        },
                    )
                    GameState.SHUFFLED, GameState.IDLE -> ActionText(
                        text = stringResource(id = R.string.click_to_play),
                        onClick = {
                            if (!uiState.playing) onPlayCard()
                        }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(playArea) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            ) {
            uiState.cards.forEachIndexed { index, card ->
                val transition =
                    updateTransition(targetState = card.cardState, label = "transition")

                val offset by transition.animateOffset(
                    transitionSpec = {
                        when (this.targetState) {
                            CardState.INITIAL -> tween(1500)
                            else -> tween(1000)
                        }
                    }, label = ""
                ) { state ->
                    when (state) {
                        CardState.SHUFFLED -> Offset(0f, screenHeight.toFloat())
                        CardState.PLAYED_BY_PLAYER, CardState.REVEALED_BY_PLAYER -> Offset(0f, 140f)
                        CardState.PLAYED_BY_OPPONENT, CardState.REVEALED_BY_OPPONENT -> Offset(
                            0f,
                            200f
                        )
                        else -> Offset(0f, 0f)
                    }
                }

                val scale by transition.animateFloat(
                    transitionSpec = {
                        tween(1000)
                    },
                    label = ""
                ) { state ->
                    when (state) {
                        CardState.INITIAL -> 1f
                        CardState.DISMISSED -> 0.75F
                        else -> 0.75f
                    }
                }

                val flipValue: Float by animateFloatAsState(
                    when (card.cardState) {
                        CardState.REVEALED_BY_PLAYER, CardState.REVEALED_BY_OPPONENT -> 0f
                        else -> 1f
                    },
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = AnticipateInterpolator().toEasing()
                    )
                )

                PlayCard(
                    modifier = Modifier
                        .scale(scale)
                        .offset(
                            x = index.dp / 2,
                            y = if (index % 2 == 1) offset.y.dp + index.dp / 2 else -offset.y.dp + index.dp / 2
                        ),
                    card = card,
                    flipValue = flipValue
                )
            }
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier
) {
    PlayerLabel(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f)),
        playerName = stringResource(id = R.string.opponent),
        orientation = LabelOrientation.FROM_TOP_TO_BOTTOM
    )
}

@Composable
private fun Footer(
    modifier: Modifier,
    userName: String,
    action: @Composable () -> Unit,
) {
    PlayerLabel(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f)),
        playerName = userName,
        orientation = LabelOrientation.FROM_BOTTOM_TO_TOP,
        action = action
    )
}

fun TimeInterpolator.toEasing() = Easing { x -> getInterpolation(x) }
