package com.dnkilic.uicomponents.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                modifier = Modifier.size(AppTheme.spaces.M),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Text(text = text)
        }
    }
}