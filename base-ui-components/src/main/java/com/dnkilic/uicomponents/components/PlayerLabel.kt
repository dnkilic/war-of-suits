package com.dnkilic.uicomponents.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.theme.AppTheme

@Composable
fun PlayerLabel(
    modifier: Modifier = Modifier,
    playerName: String,
    orientation: LabelOrientation,
    action: (@Composable () -> Unit)? = null,
) {
    when (orientation) {
        LabelOrientation.FROM_TOP_TO_BOTTOM ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(AppTheme.spaces.M),
                    text = playerName,
                    style = MaterialTheme.typography.h6
                )
            }
        LabelOrientation.FROM_BOTTOM_TO_TOP ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = playerName,
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = Modifier.height(AppTheme.spaces.M))
                action?.invoke()
                Spacer(modifier = Modifier.height(AppTheme.spaces.M))
            }
    }
}

enum class LabelOrientation {
    FROM_TOP_TO_BOTTOM,
    FROM_BOTTOM_TO_TOP,
}

@Preview
@Composable
fun PlayerLabelTopToBottomPreview() {
    PlayerLabel(
        playerName = "Preview",
        orientation = LabelOrientation.FROM_TOP_TO_BOTTOM,
    )
}

@Preview
@Composable
fun PlayerLabelBottomToTopPreview() {
    PlayerLabel(
        playerName = "Preview",
        orientation = LabelOrientation.FROM_BOTTOM_TO_TOP
    ) {
        Text(text = "Action Text")
    }
}