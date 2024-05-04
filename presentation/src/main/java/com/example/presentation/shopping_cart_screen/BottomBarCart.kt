package com.example.presentation.shopping_cart_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint


@Composable
fun BottomBarCart(
    homeViewModel: HomeViewModel,
    onClickAction: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Order Summary",
            color = GrayDark,
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(start = 7.dp, top = 11.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Totals",
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
            )
            Text(

                text = homeViewModel.getSum(),
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
            )
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(50.dp),
            onClick = { onClickAction()},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Mint),
        ) {
            Text(
                text = "Select payment method",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}


