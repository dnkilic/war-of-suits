package com.dnkilic.warofsuits.game.ui

import android.animation.TimeInterpolator
import android.view.animation.AnticipateInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dnkilic.uicomponents.components.PlayCard
import com.dnkilic.uicomponents.components.PlayCardSize
import com.dnkilic.uicomponents.components.height
import com.dnkilic.uicomponents.components.width
import com.dnkilic.warofsuits.data.model.CardState
import com.dnkilic.warofsuits.game.model.GameUiState

@Composable
fun Board(
    modifier: Modifier = Modifier,
    uiState: GameUiState,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
    ) {
        val boardSize = calculateBoardSize(maxHeight.value)

        uiState.cards.forEachIndexed { index, card ->
            val transition =
                updateTransition(targetState = card.cardState, label = "transition")

            val offset by transition.animateOffset(
                transitionSpec = {
                    tween(1000)
                }, label = "Offset transition"
            ) { state ->
                when (state) {
                    CardState.SHUFFLED -> {
                        val shuffledOffsetY = getShuffledOffsetYAbsolute(maxHeight.value, boardSize)
                        Offset(0f, shuffledOffsetY)
                    }
                    CardState.PLAYED_BY_PLAYER, CardState.PLAYED_BY_OPPONENT,
                    CardState.REVEALED_BY_PLAYER, CardState.REVEALED_BY_OPPONENT -> {
                        val playedOffsetX = getPlayedOffsetX(boardSize)
                        val playedOffsetY = getPlayedOffsetYAbsolute(boardSize)
                        Offset(playedOffsetX, playedOffsetY.value)
                    }
                    else -> Offset(0f, 0f)
                }
            }

            val scale by transition.animateFloat(
                transitionSpec = {
                    tween(1000)
                },
                label = "Scale animation"
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
                    .align(Alignment.CenterStart)
                    .padding(start = boardSize.horizontalMargin())
                    .scale(scale)
                    .offset(
                        x = applyXOffsetBy(card.cardState, index, offset.x),
                        y = applyYOffsetBy(card.cardState, index, offset.y)
                    ),
                card = card,
                flipValue = flipValue,
                playCardSize = boardSize.playCardSize()
            )
        }
    }
}

private fun applyYOffsetBy(cardState: CardState, itemIndex: Int, offsetY: Float): Dp {
    val movementByIndex = itemIndex.dp / 2
    val isPlayerCard = itemIndex % 2 == 1

    return when (cardState) {
        CardState.INITIAL, CardState.DISMISSED -> movementByIndex
        CardState.SHUFFLED ->
            if (isPlayerCard) offsetY.dp + movementByIndex else -offsetY.dp + movementByIndex
        CardState.PLAYED_BY_PLAYER, CardState.PLAYED_BY_OPPONENT,
        CardState.REVEALED_BY_PLAYER, CardState.REVEALED_BY_OPPONENT ->
            if (isPlayerCard) -offsetY.dp + movementByIndex else offsetY.dp + movementByIndex
    }
}

private fun applyXOffsetBy(cardState: CardState, itemIndex: Int, offsetX: Float): Dp {
    val movementByIndex = itemIndex.dp / 2

    return when (cardState) {
        CardState.INITIAL, CardState.DISMISSED -> movementByIndex
        CardState.SHUFFLED -> movementByIndex
        CardState.PLAYED_BY_PLAYER, CardState.PLAYED_BY_OPPONENT,
        CardState.REVEALED_BY_PLAYER, CardState.REVEALED_BY_OPPONENT -> offsetX.dp + movementByIndex
    }
}

private fun calculateBoardSize(height: Float): BoardSize {
    return when {
        height <= 600 -> BoardSize.SMALL
        height <= 800 -> BoardSize.MEDIUM
        else -> BoardSize.BIG
    }
}

private fun getShuffledOffsetYAbsolute(height: Float, boardSize: BoardSize): Float {
    val heightOfPlayCard = boardSize.playCardSize().height().value
    return (height / 2) - (heightOfPlayCard / 2)
}

private fun getPlayedOffsetYAbsolute(boardSize: BoardSize): Dp {
    return boardSize.playCardSize().height() - boardSize.verticalMargin()
}

private fun getPlayedOffsetX(boardSize: BoardSize): Float {
    val widthOfPlayCard = boardSize.playCardSize().width().value
    return widthOfPlayCard + CARD_DECK * BASE_MOVEMENT_OFFSET_PER_CARD +
            boardSize.multiplier * BASE_HORIZONTAL_MARGIN
}

private fun BoardSize.playCardSize(): PlayCardSize {
    return when (this) {
        BoardSize.SMALL -> PlayCardSize.SMALL
        BoardSize.MEDIUM -> PlayCardSize.MEDIUM
        BoardSize.BIG -> PlayCardSize.BIG
    }
}

private fun BoardSize.horizontalMargin(): Dp {
    return (this.multiplier * BASE_HORIZONTAL_MARGIN).dp
}

private fun BoardSize.verticalMargin(): Dp {
    return (this.multiplier * BASE_VERTICAL_MARGIN).dp
}

enum class BoardSize(val multiplier: Float) {
    SMALL(0.875f), MEDIUM(1f), BIG(1.5f)
}

private const val BASE_HORIZONTAL_MARGIN = 10
private const val BASE_VERTICAL_MARGIN = 80
private const val BASE_MOVEMENT_OFFSET_PER_CARD = 0.5f
private const val CARD_DECK = 26

fun TimeInterpolator.toEasing() = Easing { x -> getInterpolation(x) }
