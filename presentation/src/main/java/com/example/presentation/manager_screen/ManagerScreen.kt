package com.example.presentation.manager_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.manager_screen.common.ScreenType
import com.example.presentation.theme.GrayDarkest
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint

@Composable
fun ManagerScreen(
    navigateTo: (ScreenType) -> Unit
) {
    Column(
        modifier = Modifier.background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Text(
            text = "Manager Screen",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp),
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = GrayDarkest,
        )

        HorizontalDivider( color = GrayLighter)

        Column(
            Modifier.fillMaxSize()
                .padding(horizontal = 35.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            ActionBox(label = "Create product") { navigateTo(ScreenType.Create("product")) }
            ActionBox(label = "Update product") { navigateTo(ScreenType.Update("product")) }
            ActionBox(label = "Delete product") { navigateTo(ScreenType.Delete("product")) }
            ActionBox(label = "Create category") { navigateTo(ScreenType.Create("category")) }
            ActionBox(label = "Update category") { navigateTo(ScreenType.Update("category")) }
            ActionBox(label = "Delete category") { navigateTo(ScreenType.Delete("category")) }
        }
    }
}

@Composable
fun ActionBox(label: String, onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Mint, RoundedCornerShape(4.dp))
            .clickable() { onClick() }
    ) {
        Text(
            label,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 16.sp
        )
    }
}