package com.dnkilic.warofsuits.game.ui

import androidx.lifecycle.ViewModel
import com.dnkilic.warofsuits.game.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : IGameViewModel() {

    private val viewModelState = MutableStateFlow(GameUiState())
    override val uiState: StateFlow<GameUiState> = viewModelState

    fun resetGame() {

    }

    fun startGame() {

    }

    fun playNextCard() {

    }

    private fun setupSuitPriority() {

    }
}

abstract class IGameViewModel : ViewModel() {
    abstract val uiState: StateFlow<GameUiState>
}