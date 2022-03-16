package com.dnkilic.uicomponents.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.theme.AppTheme

@Composable
fun PlayerLabel(
    modifier: Modifier = Modifier,
    playerName: String,
    textColor: Color,
) {
    Text(
        modifier = modifier.padding(AppTheme.spaces.M),
        text = playerName,
        style = MaterialTheme.typography.subtitle1.copy(color = textColor)
    )
}

@Preview
@Composable
fun PlayerLabelTopToBottomPreview() {
    PlayerLabel(
        playerName = "Preview",
        textColor = Color.White
    )
}