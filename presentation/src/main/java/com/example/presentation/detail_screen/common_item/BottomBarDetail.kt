package com.example.presentation.detail_screen.common_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.Product
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red

@Composable
fun BottomBarDetail(
    product: Product,
    homeViewModel: HomeViewModel,
    onClickAction: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .height(55.dp),
            onClick = { homeViewModel.checkCart(product) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (homeViewModel.isContainsCart(product)) Red else Mint
            ),
        ) {
            Text(
                text = if (homeViewModel.isContainsCart(product)) "Remove from cart" else "Add to cart",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .height(55.dp),
            onClick = { onClickAction() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(GrayLighter),
            border = BorderStroke(1.dp, GrayLight)
        ) {
            Text(
                text = "Buy Now",
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}