package com.dnkilic.warofsuits.player.model

import androidx.annotation.StringRes
import com.dnkilic.warofsuits.R
import java.lang.Exception

interface Input {
    fun validate() : InputValidation
}

data class UserNameInput(val userName: String) : Input {

    @Throws(ValidateException::class)
    override fun validate(): InputValidation {
        return when {
            userName.isEmpty() -> {
                throw ValidateException(R.string.empty_input)
            }
            userName.length < MIN_LENGTH -> {
                throw ValidateException(R.string.short_input)
            }
            userName.length > MAX_LENGHT -> {
                throw ValidateException(R.string.long_input)
            }
            else -> InputValidation(userName = userName)
        }
    }

    companion object {
        private const val MIN_LENGTH = 3
        private const val MAX_LENGHT = 16
    }
}

data class InputValidation(val userName: String)

class ValidateException(
    @StringRes val error: Int? = null
) : Exception()