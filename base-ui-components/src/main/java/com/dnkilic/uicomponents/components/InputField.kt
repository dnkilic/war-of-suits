package com.dnkilic.uicomponents.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (text: String) -> Unit = {},
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    error: String? = null,
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String? = null
) {
    var isError by remember { mutableStateOf(false) }

    if (!error.isNullOrBlank()) {
        isError = true
    }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        isError = isError,
        onValueChange = {
            onValueChange(it)
            isError = false
        },
        label = { Text(text = if (isError) requireNotNull(error) else { label } ) },
        placeholder = { Text(text = if (isError) requireNotNull(error) else { placeholder } ) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(imageVector = trailingIcon, contentDescription = trailingIconContentDescription)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.primary,
            placeholderColor = MaterialTheme.colors.primary,
        )
    )
}