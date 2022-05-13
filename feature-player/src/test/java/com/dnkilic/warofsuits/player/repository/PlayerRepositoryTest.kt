package com.dnkilic.warofsuits.player.repository

import com.dnkilic.warofsuit.player.R
import com.dnkilic.warofsuits.player.model.UserNameInput
import com.dnkilic.warofsuits.player.model.ValidateException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class PlayerRepositoryTest {

    private val coroutineDispatcher = TestCoroutineDispatcher()
    private val playerRepository = PlayerRepository()

    @Test(expected = ValidateException::class)
    fun `validation fails when user name is empty`() = runBlockingTest {
        val emptyUserName = UserNameInput("")

        val subscribe = launch(coroutineDispatcher) {
            playerRepository.validate(emptyUserName).collect {  }
        }

        subscribe.cancel()
    }

    @Test
    fun `validation fails when user name is too short`() = runBlockingTest {
        val shortUserName = UserNameInput("12")

        val subscribe = launch(coroutineDispatcher) {
            playerRepository.validate(shortUserName)
                .catch {
                    assertTrue(it is ValidateException)
                    assertTrue(it is ValidateException && it.error == R.string.short_input)
                }
                .collect {  }
        }

        subscribe.cancel()
    }

    @Test
    fun `validation fails when user name is too long`() = runBlockingTest {
        val longUserName = UserNameInput((1..50).joinToString { it.toString() })

        val subscribe = launch(coroutineDispatcher) {
            playerRepository.validate(longUserName)
                .catch {
                    assertTrue(it is ValidateException)
                    assertTrue(it is ValidateException && it.error == R.string.long_input)
                }
                .collect {  }
        }

        subscribe.cancel()
    }
}