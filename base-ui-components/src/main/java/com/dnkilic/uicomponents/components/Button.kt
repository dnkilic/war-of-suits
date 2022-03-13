package com.dnkilic.uicomponents.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Button(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    androidx.compose.material.Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(text = text)
    }
}