package com.dnkilic.warofsuits.player.repository

import com.dnkilic.warofsuits.player.model.InputValidation
import com.dnkilic.warofsuits.player.model.UserNameInput
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerRepository @Inject constructor() {

    fun validate(playerName: UserNameInput): Flow<InputValidation> {
        return playerName.validate()
    }
}