package com.dnkilic.uicomponents.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.dnkilic.uicomponents.images.*

data class CardDto(
    val cardType: CardType,
    val cardValue: CardValue,
    val cardSurface: CardSurface,
    val cardState: CardState,
)

enum class CardType {
    CLUB, DIAMOND, HEARTH, SPADE
}

enum class CardValue {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

enum class CardSurface {
    FACE, BACK
}

enum class CardState {
    INITIAL, SHUFFLED, PLAYED_BY_PLAYER, PLAYED_BY_OPPONENT, REVEALED_BY_PLAYER, REVEALED_BY_OPPONENT, DISMISSED
}

fun CardType.icon(): ImageVector {
    return when (this) {
        CardType.CLUB -> Images.Club
        CardType.DIAMOND -> Images.Diamond
        CardType.HEARTH -> Images.Heart
        CardType.SPADE -> Images.Spade
    }
}

fun CardType.description(): String {
    return when (this) {
        CardType.CLUB -> "Club"
        CardType.DIAMOND -> "Diamond"
        CardType.HEARTH -> "Hearth"
        CardType.SPADE -> "Spade"
    }
}