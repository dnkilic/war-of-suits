package com.dnkilic.warofsuits.player.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnkilic.uicomponents.components.InputField
import com.dnkilic.uicomponents.components.PrimaryButton
import com.dnkilic.uicomponents.theme.AppTheme
import com.dnkilic.warofsuits.R
import com.dnkilic.warofsuits.player.model.PlayerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlayerScreen(
    playerViewModel: IPlayerViewModel,
    onStartNewGame: (String) -> Unit = {},
    onNavigateToGame: (String) -> Unit = {},
    onTextChange: () -> Unit = {},
) {
    Scaffold(
        content = {
            PlayerScreenContent(
                playerViewModel = playerViewModel,
                onStartNewGame = onStartNewGame,
                onNavigateToGame = onNavigateToGame,
                onTextChange = onTextChange,
            )
        }
    )
}

@Composable
private fun PlayerScreenContent(
    playerViewModel: IPlayerViewModel,
    onStartNewGame: (String) -> Unit = {},
    onNavigateToGame: (String) -> Unit = {},
    onTextChange: () -> Unit = {},
) {
    val uiState by playerViewModel.uiState.collectAsState()
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var userName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    LaunchedEffect(key1 = Unit) {
        playerViewModel.uiState.collectLatest {
            if (it.playerName != null) {
                onNavigateToGame(userName.text)
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = AppTheme.spaces.M)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(AppTheme.spaces.L))
        Text(
            text = stringResource(id = R.string.title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4.copy(color = Color.Black),
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.M))
        Text(
            text = stringResource(id = R.string.description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.M))
        Image(
            modifier = Modifier.height((screenHeight / 3).dp),
            painter = painterResource(id = R.drawable.cover),
            contentDescription = "Cover image",
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.M))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            text = userName.text,
            error = if (uiState.errorResId != null) {
                stringResource(id = requireNotNull(uiState.errorResId))
            } else {
                null
            },
            onValueChange = {
                onTextChange()
                userName = TextFieldValue(it)
            },
            label = stringResource(id = R.string.username),
            placeholder = stringResource(id = R.string.enter_username),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                onStartNewGame(userName.text)
            })
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.L))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.start_game),
            isLoading = uiState.loading,
            onClick = {
                keyboardController?.hide()
                onStartNewGame(userName.text)
            }
        )
    }
}

@Composable
@Preview
private fun PlayerScreenPreview() {
    PlayerScreen(playerViewModel = PreviewPlayerViewModel())
}

private class PreviewPlayerViewModel : IPlayerViewModel() {
    override val uiState: StateFlow<PlayerUiState> = MutableStateFlow(PlayerUiState())
}