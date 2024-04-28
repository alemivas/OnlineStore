package com.example.presentation.wishlist_screen.common_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Product
import com.example.presentation.R
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red
import com.example.presentation.wishlist_screen.WishlistViewModel

@Composable
fun FavoriteItem(
    wishlistViewModel: WishlistViewModel,
    product: Product,
    navigateToDetail: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(product.id) },
        colors = CardDefaults.cardColors(containerColor = GrayLightest),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = if (wishlistViewModel.isFavoriteChecked(product)) R.drawable.heart_fill
                        else R.drawable.heart
                    ),
                    modifier = Modifier.size(35.dp)
                        .clickable { wishlistViewModel.toggleFavorite(product) },
                    contentDescription = null,
                )

                Button(
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (wishlistViewModel.isContainsCart(product)) Red else Mint
                    ),
                    onClick = { wishlistViewModel.checkCart(product) }
                ) {
                    Text(
                        text = if (wishlistViewModel.isContainsCart(product)) "Remove" else "Add to cart",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                    )
                }
            }
        }
    }
}