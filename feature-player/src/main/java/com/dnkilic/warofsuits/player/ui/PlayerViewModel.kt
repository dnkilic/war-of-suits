package com.dnkilic.warofsuits.player.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnkilic.warofsuit.player.R
import com.dnkilic.warofsuits.player.model.InputValidation
import com.dnkilic.warofsuits.player.model.PlayerUiState
import com.dnkilic.warofsuits.player.model.UserNameInput
import com.dnkilic.warofsuits.player.model.ValidateException
import com.dnkilic.warofsuits.player.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val ioDispatcher: CoroutineDispatcher
) : IPlayerViewModel() {

    private val viewModelState = MutableStateFlow(PlayerUiState())
    override val uiState: StateFlow<PlayerUiState> = viewModelState

    fun startNewGame(playerName: String) {
        if (uiState.value.loading) {
            return
        }

        viewModelScope.launch(ioDispatcher) {
            playerRepository.validate(UserNameInput(playerName))
                .onStart {
                    viewModelState.update { PlayerUiState(loading = true) }
                }
                .catch { handleValidationError(it) }
                .collectLatest { handleSuccessfulValidation(it) }
        }
    }

    fun resetUiState() {
        viewModelState.update { PlayerUiState() }
    }

    private fun handleValidationError(throwable: Throwable) {
        val errorResId = when (throwable) {
            is ValidateException -> throwable.error
            else -> R.string.something_went_wrong_while_validating_user_name
        }

        viewModelState.update {
            it.copy(errorResId = errorResId, loading = false)
        }
    }

    private fun handleSuccessfulValidation(inputValidation: InputValidation) {
        val playerName = inputValidation.userName

        viewModelState.update {
            it.copy(playerName = playerName, loading = false)
        }
    }
}

abstract class IPlayerViewModel : ViewModel() {
    abstract val uiState: StateFlow<PlayerUiState>
}