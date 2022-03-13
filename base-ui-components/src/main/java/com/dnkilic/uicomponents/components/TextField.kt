package com.dnkilic.uicomponents.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (text: String) -> Unit = {},
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String? = null
) {
    androidx.compose.material.TextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(imageVector = trailingIcon, contentDescription = trailingIconContentDescription)
            }
        }
    )
}