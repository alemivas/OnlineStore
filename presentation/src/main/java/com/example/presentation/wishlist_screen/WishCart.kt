package com.example.presentation.wishlist_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.Red

@Composable
fun WishCart(
    wishlistViewModel: HomeViewModel,
    onCartClick: () -> Unit,
) {
    Box {
        Icon(
            modifier = Modifier.clickable { onCartClick() },
            painter = painterResource(id = R.drawable.cart),
            contentDescription = null,
            tint = GrayDark
        )
        if (wishlistViewModel.cart.value.isNotEmpty()) {
            Badge(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(15.dp)
                    .offset(
                        x = 2.dp,
                        y = (-2).dp
                    ),
                containerColor = Red,
                contentColor = Color.White,
                content = {
                    Text(
                        text = wishlistViewModel.cart.value.size.toString(),
                        textAlign = TextAlign.Center,
                    )
                }
            )
        }
    }
}
