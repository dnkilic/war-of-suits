package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dnkilic.uicomponents.ext.description
import com.dnkilic.uicomponents.ext.icon
import com.dnkilic.uicomponents.theme.AppTheme
import com.dnkilic.warofsuits.data.model.CardType

@Composable
fun Score(
    modifier: Modifier = Modifier,
    opponentScore: Int,
    playerScore: Int,
    textColor: Color,
    priorityList: List<CardType>,
) {
    val scoreBoardSize = calculateScoreBoardSize(LocalConfiguration.current.screenHeightDp)

    Box(modifier = modifier
        .width(scoreBoardSize.scoreBoardWidth())
    ) {
        Box(
            modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(color = MaterialTheme.colors.primary)
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.testTag(OPPONENT_SCORE_TAG),
                text = opponentScore.toString(),
                style = MaterialTheme.typography.h4.copy(color = textColor),
            )
            Spacer(modifier = Modifier.height(AppTheme.spaces.M))
            Text(
                modifier = Modifier.testTag(PLAYER_SCORE_TAG),
                text = playerScore.toString(),
                style = MaterialTheme.typography.h4.copy(color = textColor)
            )
        }

        SuitPriority(
            modifier = Modifier.align(Alignment.BottomCenter),
            priorityList = priorityList,
            iconSize = scoreBoardSize.suitPriorityIconSize()
        )
    }
}

@Composable
@Preview
private fun SuitPriorityPreview() {
    SuitPriority(
        priorityList = listOf(
            CardType.CLUB,
            CardType.DIAMOND,
            CardType.HEARTH,
            CardType.SPADE
        ),
        iconSize = ScoreBoardSize.MEDIUM.scoreBoardWidth()
    )
}

@Composable
fun SuitPriority(
    modifier: Modifier = Modifier,
    priorityList: List<CardType>,
    iconSize: Dp,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        content = {
            priorityList.forEach {
                item {
                    Image(
                        modifier = Modifier.size(iconSize),
                        imageVector = it.icon(),
                        contentDescription = it.description()
                    )
                }
            }
        })
}


@Preview
@Composable
fun ScorePreview() {
    Score(
        modifier = Modifier.height(700.dp),
        opponentScore = 9,
        playerScore = 22,
        textColor = Color.White,
        priorityList = listOf(
            CardType.CLUB, CardType.DIAMOND, CardType.SPADE, CardType.HEARTH
        )
    )
}

private fun ScoreBoardSize.scoreBoardWidth(): Dp {
    return (BASE_SCORE_BOARD_WIDTH * this.multiplier).dp
}

private fun ScoreBoardSize.suitPriorityIconSize(): Dp {
    return (BASE_SUIT_PRIORITY_ICON_SIZE * this.multiplier).dp
}

private const val BASE_SCORE_BOARD_WIDTH = 40
private const val BASE_SUIT_PRIORITY_ICON_SIZE = 35

private enum class ScoreBoardSize(val multiplier: Float) {
    SMALL(0.875f), MEDIUM(1f), BIG(1.5f)
}

private fun calculateScoreBoardSize(screenHeightInDp: Int): ScoreBoardSize {
    return when {
        screenHeightInDp <= 600 -> ScoreBoardSize.SMALL
        screenHeightInDp <= 800 -> ScoreBoardSize.MEDIUM
        else -> ScoreBoardSize.BIG
    }
}

const val PLAYER_SCORE_TAG = "PLAYER_SCORE_TAG"
const val OPPONENT_SCORE_TAG = "OPPONENT_SCORE_TAG"