package com.dnkilic.warofsuits.game.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.components.PlayerLabel
import com.dnkilic.warofsuits.game.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onResetGame: () -> Unit = {},
    tintColor: Color,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        PlayerLabel(
            modifier = Modifier.align(Alignment.Center),
            playerName = stringResource(id = R.string.opponent),
            textColor = tintColor
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { onResetGame() }
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                tint = tintColor,
                contentDescription = "Reset Button",
            )
        }
    }
}

@Composable
@Preview
private fun HeaderPreview() {
    Header(tintColor = Color.White)
}