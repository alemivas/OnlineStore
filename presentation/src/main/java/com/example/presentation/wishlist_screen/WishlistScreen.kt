package com.example.presentation.wishlist_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Product
import com.example.presentation.R
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red

@Composable
fun WishlistScreen(
    wishlistViewModel: HomeViewModel,
    navigationToSearchScreen: () -> Unit
) {
    var isFilter by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        WishSearchBar(
            wishlistViewModel = wishlistViewModel,
            isSearchScreen = false,
            padding = 0.dp,
            navigateToSearch = { navigationToSearchScreen() },
            navigateBack = {})
        val list = wishlistViewModel.favoriteList.value
        if (wishlistViewModel.favoriteList.value.isEmpty()) {
            NoResultBox("No results")
            //if (wishlistViewModel.sortedList.value.isEmpty()) NoResultBox("No results")
            //else EmptyState(wishlistViewModel, wishlistViewModel.sortedList.value)
        } else {
            FavoriteState(wishlistViewModel, list)
//            if (list.isEmpty()) NoResultBox("No results")
//            else EmptyState(wishlistViewModel, list)
        }
    }
}

@Composable
fun FavoriteState(
    wishlistViewModel: HomeViewModel,
    listProduct: List<Product>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listProduct.size) { product ->
            CardMarks(wishlistViewModel = wishlistViewModel, product = listProduct[product])
        }
    }
}

@Composable
fun CardMarks(
    wishlistViewModel: HomeViewModel,
    product: Product
) {

    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFDDDDDD))

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
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
                Row {

                    Image(
                        imageVector = ImageVector
                            .vectorResource(id = if (wishlistViewModel.isFavorite(product)) R.drawable.heart_fill else R.drawable.heart),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            wishlistViewModel.favSave(product)
                        }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (wishlistViewModel.cart.value.contains(product)) Red else Mint
                        ),
                        onClick = {
                            wishlistViewModel.checkCart(product)
                        }
                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                        )
                    }
                }
            }
        }
    }
}
