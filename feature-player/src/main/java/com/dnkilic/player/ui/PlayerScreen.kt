package com.dnkilic.player.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.dnkilic.player.R
import com.dnkilic.uicomponents.components.PrimaryButton
import com.dnkilic.uicomponents.components.TextField
import com.dnkilic.uicomponents.theme.AppTheme

@Composable
fun PlayerScreen(
    onStartNewGameButtonClick: (String) -> Unit,
) {
    Scaffold(
        content = {
            PlayerScreenContent(
                onStartNewGameButtonClick = onStartNewGameButtonClick
            )
        }
    )
}

@Composable
private fun PlayerScreenContent(
    onStartNewGameButtonClick: (String) -> Unit,
) {
    var userName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.spaces.M),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(AppTheme.spaces.L))
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.M))
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.L))
        TextField(
            text = userName.text,
            onValueChange = { userName = TextFieldValue(it) },
            label = stringResource(id = R.string.username),
            placeholder = stringResource(id = R.string.enter_username),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(AppTheme.spaces.L))
        PrimaryButton(
            modifier = Modifier,
            text = stringResource(id = R.string.start_game),
            onClick = {
                onStartNewGameButtonClick(userName.text)
            }
        )
    }
}