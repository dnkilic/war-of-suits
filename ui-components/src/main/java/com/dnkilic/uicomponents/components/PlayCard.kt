package com.dnkilic.uicomponents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnkilic.uicomponents.images.*

@Composable
fun PlayCard(
    modifier: Modifier = Modifier,
    isClosed: Boolean = false
) {
    var state by remember { mutableStateOf(if (isClosed) CardState.BACK else CardState.FRONT) }

    Card(
        modifier = modifier
            .height(224.dp)
            .width(147.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 1.dp,
    ) {
        when (state) {
            CardState.FRONT -> PlayCardFront(
                cardValue = CardValue.FIVE,
                cardType = CardType.HEARTH,
                onClick = {
                    state = CardState.BACK
                }
            )
            CardState.BACK -> PlayCardBack()
        }
    }
}

@Composable
private fun PlayCardFront(
    onClick: (Int) -> Unit,
    cardValue: CardValue,
    cardType: CardType,
) {
    Box(
        modifier = Modifier.clickable { onClick(1) }
    ) {
        PlayCardValue(
            modifier = Modifier.align(Alignment.TopStart),
            cardType = CardType.HEARTH,
            cardValue = cardValue
        )
        Image(
            modifier = Modifier.size(70.dp).align(Alignment.Center),
            imageVector = cardType.icon(),
            contentDescription = ""
        )
        PlayCardValue(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .rotate(180f),
            cardType = CardType.HEARTH,
            cardValue = cardValue
        )
    }
}

@Composable
private fun PlayCardValue(
    modifier: Modifier,
    cardValue: CardValue,
    cardType: CardType,
) {
    val textColor = when (cardType) {
        CardType.CLUB, CardType.SPADE -> Color.Black
        CardType.HEARTH, CardType.DIAMOND -> Color(0xFFF24E1E)
    }

    val value = when (cardValue) {
        CardValue.ACE, CardValue.JACK, CardValue.QUEEN,
        CardValue.KING -> cardValue.name.first().uppercase()
        CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE,
        CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE,
        CardValue.TEN -> cardValue.ordinal.plus(2).toString()
    }

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = textColor,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif
        )
        Image(
            modifier = Modifier.size(20.dp),
            imageVector = cardType.icon(),
            contentDescription = ""
        )
    }
}

@Composable
private fun PlayCardBack() {
    Surface(modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .background(Color.Blue),

        ) {

        }

    }
}

private enum class CardState {
    FRONT, BACK
}

private enum class CardType {
    CLUB, DIAMOND, HEARTH, SPADE
}

private fun CardType.icon(): ImageVector {
    return when (this) {
        CardType.CLUB -> Images.Club
        CardType.DIAMOND -> Images.Diamond
        CardType.HEARTH -> Images.Heart
        CardType.SPADE -> Images.Spade
    }
}

private enum class CardValue {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

@Preview
@Composable
fun De() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PlayCard()
    }
}