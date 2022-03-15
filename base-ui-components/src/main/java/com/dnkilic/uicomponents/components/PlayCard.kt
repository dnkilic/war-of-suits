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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnkilic.uicomponents.R
import com.dnkilic.uicomponents.ext.*
import com.dnkilic.uicomponents.theme.AppTheme
import com.dnkilic.warofsuits.data.model.*
import kotlin.math.abs

@Composable
fun PlayCard(
    modifier: Modifier = Modifier,
    flipValue: Float,
    card: CardDto,
    playCardSize: PlayCardSize,
) {
    DoubleSide(
        modifier = modifier,
        rotationY = flipValue * 180,
        front = {
            PlayCardFront(
                playCardSize = playCardSize,
                cardValue = card.cardValue,
                cardType = card.cardType)
        },
        back = {
            PlayCardBack(playCardSize = playCardSize)
        },
        playCardSize = playCardSize
    )
}

@Composable
private fun DoubleSide(
    modifier: Modifier,
    rotationY: Float = 0f,
    playCardSize: PlayCardSize,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    fun isFlipped() = (abs(rotationY) % 360 > 90f)

    if (isFlipped()) {
        val rotationYBack = -rotationY
        Card(
            modifier = modifier
                .height(playCardSize.height())
                .width(playCardSize.width())
                .graphicsLayer(
                    rotationY = rotationYBack,
                    cameraDistance = 8f
                ),
            shape = RoundedCornerShape((playCardSize.multiplier * 5).dp),
            backgroundColor = Color.White,
            elevation = 1.dp,
        ) {
            back()
        }
    } else {
        Card(
            modifier = modifier
                .height(playCardSize.height())
                .width(playCardSize.width())
                .graphicsLayer(
                    rotationY = rotationY,
                    cameraDistance = 8f
                ),
            shape = RoundedCornerShape((playCardSize.multiplier * 5).dp),
            backgroundColor = Color.White,
            elevation = (playCardSize.multiplier * 0.5).dp,
        ) {
            front()
        }
    }
}

@Composable
private fun PlayCardFront(
    cardValue: CardValue,
    cardType: CardType,
    playCardSize: PlayCardSize,
) {
    Box {
        PlayCardValue(
            modifier = Modifier.align(Alignment.TopStart),
            cardType = cardType,
            cardValue = cardValue,
            playCardSize = playCardSize,
        )
        Image(
            modifier = Modifier
                .size(playCardSize.centerIconSize())
                .align(Alignment.Center),
            imageVector = cardType.icon(),
            contentDescription = ""
        )
        PlayCardValue(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .rotate(180f),
            cardType = cardType,
            cardValue = cardValue,
            playCardSize = playCardSize,
        )
    }
}

@Composable
private fun PlayCardValue(
    modifier: Modifier,
    cardValue: CardValue,
    cardType: CardType,
    playCardSize: PlayCardSize,
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
            .padding(
                horizontal = (playCardSize.multiplier * 6).dp,
                vertical = (playCardSize.multiplier * 3).dp
            )
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = textColor,
            fontSize = playCardSize.fontSize().sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif
        )
        Image(
            modifier = Modifier.size(playCardSize.iconSize()),
            imageVector = cardType.icon(),
            contentDescription = ""
        )
    }
}

@Composable
private fun PlayCardBack(
    playCardSize: PlayCardSize
) {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape((playCardSize.multiplier * 4).dp),
    ) {
        Image(
            painterResource(R.drawable.card_back),
            contentScale = ContentScale.FillBounds,
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

    Column {
        PlayCard(
            card = card,
            flipValue = 0f,
            playCardSize = PlayCardSize.BIG
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.S))
        PlayCard(card = card,
            flipValue = 0f,
            playCardSize = PlayCardSize.MEDIUM
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.S))
        PlayCard(
            card = card,
            flipValue = 0f,
            playCardSize = PlayCardSize.SMALL
        )
    }
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

    PlayCard(
        card = card,
        flipValue = 1f,
        playCardSize = PlayCardSize.MEDIUM
    )
}

enum class PlayCardSize(val multiplier: Float) {
    SMALL(0.875f), MEDIUM(1f), BIG(1.5f)
}

private const val MIN_CARD_HEIGHT = 224
private const val MIN_CARD_WIDTH = 160
private const val MIN_CENTER_ICON_SIZE = 70
private const val MIN_CENTER_FONT_SIZE = 30
private const val MIN_ICON_SIZE = 20

private fun PlayCardSize.height(): Dp {
    return (MIN_CARD_HEIGHT * this.multiplier).dp
}

private fun PlayCardSize.width(): Dp {
    return (MIN_CARD_WIDTH * this.multiplier).dp
}

private fun PlayCardSize.centerIconSize(): Dp {
    return (MIN_CENTER_ICON_SIZE * this.multiplier).dp
}

private fun PlayCardSize.fontSize(): Float {
    return MIN_CENTER_FONT_SIZE * this.multiplier
}

private fun PlayCardSize.iconSize(): Dp {
    return (MIN_ICON_SIZE * this.multiplier).dp
}