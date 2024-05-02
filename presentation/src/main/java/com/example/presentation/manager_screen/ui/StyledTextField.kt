package com.example.presentation.manager_screen.ui

import androidx.compose.foundation.shape.RoundedCornerShape
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
    onValueChange: (TextFieldValue) -> Unit = {}
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {
            value.value = it
            onValueChange(it)
        },
        shape = RoundedCornerShape(3.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.Gray,
        )
    )
}
