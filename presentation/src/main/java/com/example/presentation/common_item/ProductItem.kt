package com.example.presentation.common_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Product
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Pink

@Composable
fun ProductItem(
    homeViewModel: HomeViewModel,
    product: Product,
    navigationToDetailScreen: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                homeViewModel.setProduct(product)
                navigationToDetailScreen()
            },
        colors = CardDefaults.cardColors(containerColor = GrayLightest),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth()
                .height(112.dp),
            contentScale = ContentScale.Crop,
            model = product.images.first().removePrefix("[\"").removeSuffix("\"]"),
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = product.title,
                color = GrayDark,
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                maxLines = 1,
            )
            Text(
                text = "$${product.price}",
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                maxLines = 1,
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (homeViewModel.cart.value.contains(product)) Pink else Mint
                ),
                onClick = { homeViewModel.checkCart(product) }
            ) {
                Text(
                    text = if (homeViewModel.cart.value.contains(product)) "Remove from cart" else "Add to cart",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                )
            }
        }
    }
}