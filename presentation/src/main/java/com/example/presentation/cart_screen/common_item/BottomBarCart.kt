package com.example.presentation.cart_screen.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.Mint

@Composable
fun BottomBarCart(
    homeViewModel: HomeViewModel,
    onClickAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Order Summary",
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            color = GrayDark,
        )
        Row {
            Text(
                text = "Totals",
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                color = GrayDark,
            )
            Text(
                text = homeViewModel.getSum(),
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = GrayDark,
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(Mint),
            onClick = {
                if (homeViewModel.cart.value.isNotEmpty()) { onClickAction() }
            }
        ) {
            Text(text = "Select payment method")
        }
    }
}