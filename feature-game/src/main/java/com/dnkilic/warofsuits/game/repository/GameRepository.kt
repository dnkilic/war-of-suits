package com.dnkilic.warofsuits.game.repository

import com.dnkilic.uicomponents.model.*
import com.dnkilic.warofsuits.game.model.Gamer
import javax.inject.Inject

class GameRepository @Inject constructor() {

    fun getDeck(): List<CardDto> {
        return CardType.values().flatMap { cardType ->
            CardValue.values().map { cardValue ->
                CardDto(
                    cardType = cardType,
                    cardValue = cardValue,
                    cardSurface = CardSurface.BACK,
                    cardState = CardState.INITIAL
                )
            }
        }.shuffled()
    }

    fun getSuitPriority(): List<CardType> {
        return CardType.values().asList().shuffled()
    }

    fun getRoundWinner(playerCard: CardDto, opponentCard: CardDto, priority: List<CardType>): Gamer {
        val (player, opponent) = when (playerCard.cardValue) {
            opponentCard.cardValue -> {
                val firstCardIndex = priority.indexOf(playerCard.cardType)
                val secondCardIndex = priority.indexOf(opponentCard.cardType)
                firstCardIndex to secondCardIndex
            }
            else -> {
                playerCard.cardValue.ordinal to opponentCard.cardValue.ordinal
            }
        }

        return if (player > opponent) {
            Gamer.Player
        } else {
            Gamer.Opponent
        }
    }
}