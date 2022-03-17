package com.dnkilic.uicomponents.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.dnkilic.uicomponents.theme.AppTheme

@Composable
fun PrimaryButton(
    modifier: Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier.height(48.dp),
        onClick = onClick,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(AppTheme.spaces.M)
                    .testTag(PRIMARY_BUTTON_PROGRESS_INDICATOR_TAG),
                color = Color.White,
                strokeWidth = 2.dp,
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.button.copy(color = Color.White)
            )
        }
    }
}

const val PRIMARY_BUTTON_PROGRESS_INDICATOR_TAG = "PRIMARY_BUTTON_PROGRESS_INDICATOR_TAG"