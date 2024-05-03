package com.example.presentation.shopping_cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Cart
import com.example.presentation.R
import com.example.presentation.common_item.Cart
import com.example.presentation.detail_screen.common_item.DetailTopBar
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.home_screen.common_item.CartItem
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.utils.Constants


@Composable
fun ShoppingCart(
    homeViewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val countryList = Constants.Country.entries.map { it.toString() }
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            DetailTopBar(
                homeViewModel = homeViewModel,
                isDetailScreen = false,
                title = "Your Cart",
                navigateToCart = {},
                navigateBack = { navigateBack() }
            )
            HorizontalDivider(color = GrayLighter)
        },
        bottomBar = {
            HorizontalDivider(color = GrayLighter)
            BottomBarCart(homeViewModel = homeViewModel) {
                showBottomSheet = true
                homeViewModel.makeOrder()
            }
        },
        containerColor = Color.White
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Delivery to",
                    modifier = Modifier.weight(1f),
                    color = GrayDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )
                Row(
                    modifier = Modifier.clickable { expanded = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = homeViewModel.currentCountry.value,
                        color = GrayDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = GrayDark
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(GrayLightest)
                    ) {
                        repeat(countryList.size) {
                            DropdownMenuItem(
                                text = { Text(countryList[it]) },
                                onClick = {
                                    homeViewModel.changeCurrentCountry(countryList[it])
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }
            HorizontalDivider(color = GrayLighter)

            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(homeViewModel.cart.value.size) {
                    CartItem(
                        homeViewModel = homeViewModel,
                        cart = homeViewModel.cart.value[it],
                        navigateToDetail = navigateToDetail
                    )
                }
            }

        }
    }
}
//@Composable
//fun Product(
//    homeViewModel: HomeViewModel,
//    cart: Cart,
//    navigateToDetail: (Int) -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { },
//    ) {
//        AsyncImage(
//            modifier = Modifier
//                .fillMaxHeight()
//                .aspectRatio(1f)
//                .clip(RoundedCornerShape(8.dp)),
//            contentScale = ContentScale.Crop,
//            model = cart.product.images.first().removePrefix("[\"").removeSuffix("\"]"),
//            contentDescription = null,
//        )
//        Column(
//            verticalArrangement = Arrangement.spacedBy(6.dp)
//        ) {
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp),
//                text = cart.product.title,
//                color = GrayDark,
//                fontSize = 12.sp,
//                fontWeight = FontWeight(400),
//                maxLines = 1,
//
//                )
//            Row {
//                Text(
//                    text = homeViewModel.getConvertedPrice(cart.product.price),
//                    color = GrayDark,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight(600),
//                    maxLines = 1,
//                    modifier = Modifier.padding(top = 50.dp, start = 8.dp)
//                )
//                IconButton(
//                    onClick = { homeViewModel.removeFromCart(cart)  },
//                    modifier = Modifier.padding(top = 33.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_remove_circle_24),
//                        contentDescription = null,
//                        modifier = Modifier.padding(10.dp),
//                        tint = Gray
//                    )
//                }
//                Text(
//                    text = cart.quantity.toString(),
//                    color = GrayDark,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight(600),
//                    maxLines = 1,
//                    modifier = Modifier.padding(top = 48.dp)
//                )
//                IconButton(
//                    onClick = { homeViewModel.addToCart(cart) },
//                    modifier = Modifier.padding(top = 33.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_add_circle_24),
//                        contentDescription = null,
//                        modifier = Modifier.padding(10.dp),
//                        tint = Gray
//                    )
//                }
//                IconButton(
//                    onClick = { homeViewModel.deleteFromCart(cart) },
//                    modifier = Modifier.padding(top = 33.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_delete_24),
//                        contentDescription = null,
//                        modifier = Modifier.padding(2.dp),
//                        tint = Gray
//                    )
//                }
//            }
//
//        }
//    }
//}

//
