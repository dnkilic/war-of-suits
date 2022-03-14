package com.dnkilic.warofsuits.game.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnkilic.warofsuits.data.model.CardState
import com.dnkilic.warofsuits.game.model.GameState
import com.dnkilic.warofsuits.game.model.GameUiState
import com.dnkilic.warofsuits.game.model.Gamer
import com.dnkilic.warofsuits.game.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
) : IGameViewModel() {

    private val viewModelState = MutableStateFlow(initialUiState())
    override val uiState: StateFlow<GameUiState> = viewModelState

    fun resetGame() {
        viewModelState.update {
            initialUiState()
        }
    }

    fun startGame() {
        viewModelState.update {
            it.copy(
                gameState = GameState.SHUFFLED,
                cards = it.cards.map { card -> card.copy(cardState = CardState.SHUFFLED) }
            )
        }
    }

    fun playNextCard() {
        if (viewModelState.value.playing) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val playerCard = viewModelState.value.cards.last()
            val opponentCard = viewModelState.value.cards[viewModelState.value.cards.size - 2]
            viewModelState.update {
                val updatedPlayerCard = playerCard.copy(cardState = CardState.PLAYED_BY_PLAYER)
                val updatedOpponentCard = opponentCard.copy(cardState = CardState.PLAYED_BY_OPPONENT)
                val cards = it.cards.dropLast(2) + updatedOpponentCard + updatedPlayerCard
                it.copy(cards = cards, playing = true)
            }
            delay(2000)
            viewModelState.update {
                val updatedPlayerCard = playerCard.copy(cardState = CardState.REVEALED_BY_PLAYER)
                val updatedOpponentCard = opponentCard.copy(cardState = CardState.REVEALED_BY_OPPONENT)
                val cards = it.cards.dropLast(2) + updatedOpponentCard + updatedPlayerCard
                it.copy(cards = cards)
            }
            delay(2000)
            viewModelState.update {
                val winner = gameRepository.getRoundWinner(playerCard, opponentCard, it.suitPriority)
                val cards = it.cards.dropLast(2)
                it.copy(
                    gameState = if (cards.isEmpty()) { GameState.OVER } else { GameState.IDLE },
                    cards = cards,
                    playerPoints = if (winner == Gamer.Player) it.playerPoints + 1 else it.playerPoints,
                    opponentPoints = if (winner == Gamer.Opponent) it.opponentPoints + 1 else it.opponentPoints,
                    playing = false
                )
            }
        }
    }

    private fun initialUiState(): GameUiState {
        return GameUiState(
            loading = false,
            cards = gameRepository.getDeck(),
            gameState = GameState.INITIAL,
            suitPriority = gameRepository.getSuitPriority()
        )
    }
}

abstract class IGameViewModel : ViewModel() {
    abstract val uiState: StateFlow<GameUiState>
}