package com.example.presentation.common_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(
    count: Int,
    onCartClick: () -> Unit,
) {
    Box {
        Icon(
            modifier = Modifier.clickable { onCartClick() },
            painter = painterResource(id = R.drawable.cart),
            contentDescription = null,
            tint = Color(0xFF393F42)
        )
        Badge(
            modifier = Modifier
                .size(14.dp)
                .offset(
                    x = 16.dp,
                    y = (-1).dp
                ),
            containerColor = Color(0xFFD65B5B),
            contentColor = Color.White,
            content = {
                Text(
                    text = count.toString(),
                    textAlign = TextAlign.Center,
                )
            }
        )
    }
}
