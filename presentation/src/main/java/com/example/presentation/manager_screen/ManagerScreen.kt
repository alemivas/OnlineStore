package com.example.presentation.manager_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ManagerScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            ActionBox(label = "Create product") {}
            Spacer(Modifier.padding(8.dp))
            ActionBox(label = "Delete product") {}
            Spacer(Modifier.padding(8.dp))
            ActionBox(label = "Update product") {}
            Spacer(Modifier.padding(8.dp))
            ActionBox(label = "Create category") {}
            Spacer(Modifier.padding(8.dp))
            ActionBox(label = "Delete category") {}
            Spacer(Modifier.padding(8.dp))
            ActionBox(label = "Update category") {}
        }
    }
}

@Composable
fun ActionBox(label: String, onClick: () -> Unit) {
    Box(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.Cyan)
            .clip(RoundedCornerShape(3.dp))
            .clickable() { onClick() }
    ) {
        Text(label)
    }
}