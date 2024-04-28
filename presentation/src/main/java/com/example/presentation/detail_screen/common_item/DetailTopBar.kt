package com.example.presentation.detail_screen.common_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.common_item.Cart
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark

@Composable
fun DetailTopBar(
    homeViewModel: HomeViewModel,
    isDetailScreen: Boolean,
    title:String,
    navigateToCart: () -> Unit,
    navigateBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null,
            modifier = Modifier.clickable { navigateBack() },
            tint = GrayDark
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f)
                .padding(16.dp),
            fontWeight = FontWeight(500),
            textAlign = if (isDetailScreen) TextAlign.Center else TextAlign.Start,
            fontSize = 16.sp,
            color = GrayDark,
        )
        Cart(homeViewModel.cart.value.size) { navigateToCart() }
    }
}