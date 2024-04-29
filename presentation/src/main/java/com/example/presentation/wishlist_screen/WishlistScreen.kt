package com.example.presentation.wishlist_screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Product
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.common_item.SearchBar
import com.example.presentation.common_item.TopBar
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red
import com.example.utils.ApiResult

@Composable
fun WishlistScreen(
    wishlistViewModel: WishlistViewModel ,
    navigationToSearchScreen: () -> Unit
) {
    val products by wishlistViewModel.products.collectAsState()
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
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (products) {
                is ApiResult.Error -> {
                    Toast.makeText(LocalContext.current, products.error, Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp), color = GrayLight)
                }

                is ApiResult.Success -> {
                    val list = products.data ?: emptyList()
                    if (isFilter) {
                        if (wishlistViewModel.sortedList.value.isEmpty()) NoResultBox("No results")
                        else EmptyState(wishlistViewModel, wishlistViewModel.sortedList.value)
                    } else {
                        if (list.isEmpty()) NoResultBox("No results")
                        else EmptyState(wishlistViewModel, list)
                    }
                }
            }

        }
    }
}
@Composable
fun EmptyState(
    wishlistViewModel: WishlistViewModel,
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
    wishlistViewModel: WishlistViewModel,
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
                Row {
                    var isPressed by remember { mutableStateOf(false) }
                    IconButton(onClick = {isPressed = !isPressed  }) {
                        Icon(
                            imageVector = if (isPressed) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite ,
                            contentDescription = "Favorite"
                        )
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            // containerColor = if (wishlistViewModel.cart.value.contains(product)) Red else Mint
                        ),
                        onClick = {
                            //wishlistViewModel.checkCart(product)
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
