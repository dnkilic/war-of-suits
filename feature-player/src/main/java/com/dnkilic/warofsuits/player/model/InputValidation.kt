package com.dnkilic.warofsuits.player.model

import androidx.annotation.StringRes
import com.dnkilic.warofsuit.player.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Input {
    fun validate() : Flow<InputValidation>
}

data class UserNameInput(val userName: String) : Input {

    @Throws(ValidateException::class)
    override fun validate(): Flow<InputValidation> {
        return flow {
            when {
                userName.isEmpty() -> {
                    throw ValidateException(R.string.empty_input)
                }
                userName.length < MIN_LENGTH -> {
                    throw ValidateException(R.string.short_input)
                }
                userName.length > MAX_LENGHT -> {
                    throw ValidateException(R.string.long_input)
                }
                else -> emit(InputValidation(userName = userName))
            }
        }
    }

    companion object {
        private const val MIN_LENGTH = 3
        private const val MAX_LENGHT = 24
    }
}

data class InputValidation(val userName: String)

class ValidateException(
    @StringRes val error: Int? = null
) : Exception()