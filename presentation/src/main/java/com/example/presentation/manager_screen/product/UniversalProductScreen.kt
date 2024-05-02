package com.example.presentation.manager_screen.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed class ScreenType(val label: String, val model: String) {
    class Create(model: String) : ScreenType("Create", model)
    class Update(model: String) : ScreenType("Update", model)
    class Delete(model: String) : ScreenType("Delete", model)
}

@Composable
fun UniversalProductScreen(type: ScreenType) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            Button(onClick = {}) {
                Text(text = "${type.label} ${type.model}")
            }
        }
    }
}