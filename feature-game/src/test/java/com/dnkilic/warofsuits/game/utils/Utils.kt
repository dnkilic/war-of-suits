package com.dnkilic.warofsuits.game.utils

import com.dnkilic.warofsuits.data.model.*

fun fakeSuitPriority() = listOf(
    CardType.CLUB,
    CardType.DIAMOND,
    CardType.SPADE,
    CardType.HEARTH
)

fun fakeDeck() = listOf(
    CardDto(
        CardType.HEARTH,
        CardValue.EIGHT,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.EIGHT,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.EIGHT,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.EIGHT,
        CardSurface.BACK,
        CardState.INITIAL
    )
)

fun fakeCard() = CardDto(
    CardType.HEARTH,
    CardValue.EIGHT,
    CardSurface.BACK,
    CardState.INITIAL
)

fun cardDeckPlayerWins() = listOf(
    CardDto(
        CardType.DIAMOND,
        CardValue.THREE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.CLUB,
        CardValue.ACE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.TWO,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.ACE,
        CardSurface.BACK,
        CardState.INITIAL
    )
)

fun cardDeckOpponentWins() = listOf(
    CardDto(
        CardType.DIAMOND,
        CardValue.ACE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.CLUB,
        CardValue.ACE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.THREE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.TWO,
        CardSurface.BACK,
        CardState.INITIAL
    )
)

fun cardDeckDraw() = listOf(
    CardDto(
        CardType.DIAMOND,
        CardValue.ACE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.SPADE,
        CardValue.ACE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.CLUB,
        CardValue.THREE,
        CardSurface.BACK,
        CardState.INITIAL
    ),
    CardDto(
        CardType.HEARTH,
        CardValue.THREE,
        CardSurface.BACK,
        CardState.INITIAL
    )
)

fun cardSuitPlayerWins() = listOf(
    CardType.SPADE,
    CardType.HEARTH,
    CardType.CLUB,
    CardType.DIAMOND
)

fun cardSuitOpponentWins() = listOf(
    CardType.CLUB,
    CardType.DIAMOND,
    CardType.SPADE,
    CardType.HEARTH
)

fun cardSuitDraw() = listOf(
    CardType.HEARTH,
    CardType.CLUB,
    CardType.DIAMOND,
    CardType.SPADE
)