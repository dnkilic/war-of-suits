package com.dnkilic.warofsuits.game.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.dnkilic.uicomponents.components.ACTION_TEXT_TAG
import com.dnkilic.uicomponents.components.PRIMARY_BUTTON_PROGRESS_INDICATOR_TAG
import com.dnkilic.uicomponents.theme.WarOfSuitsTheme
import com.dnkilic.warofsuits.game.model.GameState
import com.dnkilic.warofsuits.game.model.GameUiState
import kotlinx.coroutines.flow.update
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = FakeGameViewModel()
    private val playerName = "Dogan Kilic"

    @Before
    fun setup() {
        composeTestRule.setContent {
            WarOfSuitsTheme {
                GameScreen(
                    playerName = playerName,
                    gameViewModel = viewModel
                )
            }
        }
    }

    @Test
    fun updatePlayerScore() {
        viewModel.viewModelState.update {
            GameUiState(playerPoints = 15)
        }

        composeTestRule.onNode(hasTestTag(PLAYER_SCORE_TAG))
            .assert(hasText("15"))
    }

    @Test
    fun updateOpponentScore() {
        viewModel.viewModelState.update {
            GameUiState(opponentPoints = 18)
        }

        composeTestRule.onNode(hasTestTag(OPPONENT_SCORE_TAG))
            .assert(hasText("18"))
    }

    @Test
    fun finishGame() {
        viewModel.viewModelState.update {
            GameUiState(
                gameState = GameState.OVER
            )
        }

        composeTestRule.onNode(hasTestTag(ACTION_TEXT_TAG))
            .assert(hasText("Click here to play again"))
            .assertExists()
    }

    @Test
    fun startGame() {
        viewModel.viewModelState.update {
            GameUiState(
                gameState = GameState.INITIAL
            )
        }

        composeTestRule.onNode(hasTestTag(ACTION_TEXT_TAG))
            .assert(hasText("Click here to start"))
            .assertExists()
    }

    @Test
    fun shuffleCards() {
        viewModel.viewModelState.update {
            GameUiState(
                gameState = GameState.SHUFFLED
            )
        }

        composeTestRule.onNode(hasTestTag(ACTION_TEXT_TAG))
            .assert(hasText("Click here to play"))
            .assertExists()
    }
}