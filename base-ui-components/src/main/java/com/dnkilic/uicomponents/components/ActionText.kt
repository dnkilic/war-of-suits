package com.dnkilic.uicomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ActionText(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: (Int) -> Unit = {},
) {
    ClickableText(
        modifier = modifier.background(Color.Cyan.copy(alpha = 0.1f)),
        text = AnnotatedString(text),
        style = MaterialTheme.typography.button.copy(
            color = Color.White,
            fontSize = 18.sp
        ),
        onClick = onClick,
    )
}

@Preview
@Composable
fun ActionTextPreview() {
    ActionText()
}