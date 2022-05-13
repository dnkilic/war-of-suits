package com.dnkilic.warofsuits.player.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.dnkilic.uicomponents.components.PRIMARY_BUTTON_PROGRESS_INDICATOR_TAG
import com.dnkilic.uicomponents.theme.WarOfSuitsTheme
import com.dnkilic.warofsuit.player.R
import com.dnkilic.warofsuits.player.model.PlayerUiState
import kotlinx.coroutines.flow.update
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlayerScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = FakePlayerViewModel()

    @Before
    fun setup() {
        composeTestRule.setContent {
            WarOfSuitsTheme {
                PlayerScreen(
                    playerViewModel = viewModel
                )
            }
        }
    }

    @Test
    fun showLoadingIndicator() {
        viewModel.viewModelState.update {
            PlayerUiState(loading = true)
        }

        composeTestRule.onNode(hasTestTag(PRIMARY_BUTTON_PROGRESS_INDICATOR_TAG))
            .assertExists()
    }

    @Test
    fun showErrorMessage() {
        viewModel.viewModelState.update {
            PlayerUiState(errorResId = R.string.short_input)
        }

        composeTestRule.onNodeWithTag(PLAYER_NAME_TEXT_FIELD_TAG).assert(
            hasText("Player name is too short")
        )
    }
}