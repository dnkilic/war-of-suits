package com.dnkilic.warofsuits.player.repository

import com.dnkilic.warofsuits.player.model.InputValidation
import com.dnkilic.warofsuits.player.model.UserNameInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PlayerRepository @Inject constructor() {

    fun validate(playerName: UserNameInput): Flow<InputValidation> {
        return flowOf(playerName.validate())
    }
}