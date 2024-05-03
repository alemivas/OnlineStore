package com.example.presentation.manager_screen.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun StyledTextField(
    value: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    options: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable() (() -> Unit)? = null,
    onValueChange: (TextFieldValue) -> Unit = {}
) {
    OutlinedTextField(
        readOnly = readOnly,
        value = value.value,
        onValueChange = {
            value.value = it
            onValueChange(it)
        },
        keyboardOptions = options,
        modifier = modifier,
        minLines = minLines,
        maxLines = maxLines,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color.DarkGray,
            unfocusedLabelColor = Color.Gray,
        )
    )
}
