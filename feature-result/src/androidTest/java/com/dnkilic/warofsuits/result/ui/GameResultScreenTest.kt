package com.dnkilic.warofsuits.result.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.dnkilic.uicomponents.theme.WarOfSuitsTheme
import com.dnkilic.warofsuits.result.R
import com.dnkilic.warofsuits.result.model.GameResultUiState
import org.junit.Rule
import org.junit.Test

class GameResultScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val playerName = "Dogan Kilic"

    @Test
    fun showMatchResultAsPlayerWins() {
        composeTestRule.setContent {
            WarOfSuitsTheme {
                GameResultScreen(GameResultUiState(1, 0, playerName))
            }
        }

        composeTestRule.onNodeWithTag(SCORE_TEXT_TAG)
            .assert(hasText(composeTestRule.activity.getString(R.string.match_score, 1, 0)))

        composeTestRule.onNodeWithTag(TITLE_TEXT_TAG)
            .assert(hasText(composeTestRule.activity.getString(R.string.player_won, playerName)))
    }

    @Test
    fun showMatchResultAsOpponentWins() {
        composeTestRule.setContent {
            WarOfSuitsTheme {
                GameResultScreen(GameResultUiState(0, 1, playerName))
            }
        }

        composeTestRule.onNodeWithTag(SCORE_TEXT_TAG)
            .assert(hasText(composeTestRule.activity.getString(R.string.match_score, 0, 1)))

        composeTestRule.onNodeWithTag(TITLE_TEXT_TAG)
            .assert(hasText(composeTestRule.activity.getString(R.string.player_lose, playerName)))
    }

    @Test
    fun showMatchResultAsDraw() {
        composeTestRule.setContent {
            WarOfSuitsTheme {
                GameResultScreen(GameResultUiState(1, 1, playerName))
            }
        }

        composeTestRule.onNodeWithTag(SCORE_TEXT_TAG)
            .assert(hasText(composeTestRule.activity.getString(R.string.match_score, 1, 1)))

        composeTestRule.onNodeWithTag(TITLE_TEXT_TAG)
            .assert(hasText(composeTestRule.activity.getString(R.string.draw)))
    }
}