package com.dnkilic.warofsuits.player.model

import androidx.annotation.StringRes

data class PlayerUiState(
    @StringRes val errorResId: Int? = null,
    val playerName: String? = null,
    val loading: Boolean = false
)