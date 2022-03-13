package com.dnkilic.warofsuits.game.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : IGameViewModel() {

    fun resetGame() {

    }

    fun startGame() {

    }

    fun playNextCard() {

    }

    private fun setupSuitPriority() {

    }
}

abstract class IGameViewModel : ViewModel()