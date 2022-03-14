package com.dnkilic.uicomponents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnkilic.uicomponents.R
import com.dnkilic.uicomponents.ext.*
import com.dnkilic.warofsuits.data.model.*
import kotlin.math.abs

@Composable
fun PlayCard(
    modifier: Modifier = Modifier,
    flipValue: Float,
    card: CardDto,
) {
    DoubleSide(
        modifier = modifier,
        rotationY = flipValue * 180,
        front = { PlayCardFront(cardValue = card.cardValue, cardType = card.cardType) },
        back = { PlayCardBack() }
    )
}

@Composable
private fun DoubleSide(
    modifier: Modifier,
    rotationY: Float = 0f,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    fun isFlipped() = (abs(rotationY) % 360 > 90f)

    if (isFlipped()) {
        val rotationYBack = -rotationY
        Card(
            modifier = modifier
                .height(224.dp)
                .width(147.dp)
                .graphicsLayer(
                    rotationY = rotationYBack,
                    cameraDistance = 8f
                ),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            elevation = 1.dp,
        ) {
            back()
        }
    } else {
        Card(
            modifier = modifier
                .height(224.dp)
                .width(147.dp)
                .graphicsLayer(
                    rotationY = rotationY,
                    cameraDistance = 8f
                ),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            elevation = 1.dp,
        ) {
            front()
        }
    }
}

@Composable
private fun PlayCardFront(
    cardValue: CardValue,
    cardType: CardType,
) {
    Box {
        PlayCardValue(
            modifier = Modifier.align(Alignment.TopStart),
            cardType = cardType,
            cardValue = cardValue
        )
        Image(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.Center),
            imageVector = cardType.icon(),
            contentDescription = ""
        )
        PlayCardValue(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .rotate(180f),
            cardType = cardType,
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
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(
            painterResource(R.drawable.card_back),
            contentDescription = "Back Poker Card",
        )
    }
}

@Preview
@Composable
private fun PreviewPlayCardFace() {
    val card = CardDto(
        cardType = CardType.DIAMOND,
        cardValue = CardValue.EIGHT,
        cardSurface = CardSurface.FACE,
        cardState = CardState.DISMISSED
    )

    PlayCard(card = card, flipValue = 0f)
}

@Preview
@Composable
private fun PreviewPlayCardBack() {
    val card = CardDto(
        cardType = CardType.CLUB,
        cardValue = CardValue.EIGHT,
        cardSurface = CardSurface.BACK,
        cardState = CardState.DISMISSED
    )

    PlayCard(card = card, flipValue = 1f)
}