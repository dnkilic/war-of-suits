package com.dnkilic.warofsuits.game.repository

import com.dnkilic.warofsuits.data.model.*
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

    /**
     * Priority from first to last item
     */
    fun getSuitPriority(): List<CardType> {
        return CardType.values().asList().shuffled()
    }

    fun getRoundWinner(playerCard: CardDto, opponentCard: CardDto, priority: List<CardType>): Gamer {
        return when (playerCard.cardValue) {
            opponentCard.cardValue -> {
                val firstCardIndex = priority.indexOf(playerCard.cardType)
                val secondCardIndex = priority.indexOf(opponentCard.cardType)
                if (firstCardIndex < secondCardIndex) {
                    Gamer.Player
                } else {
                    Gamer.Opponent
                }
            }
            else -> {
                if (playerCard.cardValue.ordinal > opponentCard.cardValue.ordinal) {
                    Gamer.Player
                } else {
                    Gamer.Opponent
                }
            }
        }
    }
}