package com.dnkilic.warofsuits.data.model

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