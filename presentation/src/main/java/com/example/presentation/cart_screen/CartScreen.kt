package com.example.presentation.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.cart_screen.common_item.CartItem

@Composable
@Preview
fun CartScreenPreview() {
    CartScreen(navigateBack = {})
}

@Composable
fun CartScreen(
    navigateBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
//        DetailTopBar(
//            homeViewModel = homeViewModel,
//            isDetailScreen = false,
//            title = "Detail product",
//            navigateBack = { navigateBack() }
//        )
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) {
                CartItem()
            }
        }
    }
}