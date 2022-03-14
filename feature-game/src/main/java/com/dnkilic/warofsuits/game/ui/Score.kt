package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dnkilic.uicomponents.model.CardType
import com.dnkilic.uicomponents.model.description
import com.dnkilic.uicomponents.model.icon
import com.dnkilic.uicomponents.theme.AppTheme

@Composable
fun Score(
    modifier: Modifier = Modifier,
    playerScore: Int,
    opponentScore: Int,
    priorityList: List<CardType>
) {
    Box(
        modifier = modifier
            .zIndex(1f)
            .fillMaxHeight()
            .width(40.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = opponentScore.toString(),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(AppTheme.spaces.M))
            Text(
                text = playerScore.toString(),
                style = MaterialTheme.typography.h4
            )
        }
        SuitPriority(
            modifier = Modifier.align(Alignment.BottomCenter),
            priorityList = priorityList,
        )
    }
}

@Composable
fun SuitPriority(
    modifier: Modifier = Modifier,
    priorityList: List<CardType>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        content = {
            priorityList.forEach {
                item {
                    Image(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = it.icon(),
                        contentDescription = it.description()
                    )
                }
            }
        })
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
        )
    )
}

@Preview
@Composable
fun ScorePreview() {
    Score(
        playerScore = 22,
        opponentScore = 9,
        priorityList = listOf(CardType.CLUB, CardType.DIAMOND, CardType.HEARTH, CardType.SPADE)
    )
}