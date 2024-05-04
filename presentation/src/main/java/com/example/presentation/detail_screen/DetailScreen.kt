package com.example.presentation.detail_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.common_item.PaymentBottomSheet
import com.example.presentation.detail_screen.common_item.DetailBottomBar
import com.example.presentation.detail_screen.common_item.DetailTopBar
import com.example.presentation.detail_screen.common_item.ImageBox
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint
import com.example.presentation.wishlist_screen.WishlistViewModel

@Composable
fun DetailScreen(
    wishlistViewModel: WishlistViewModel,
    homeViewModel: HomeViewModel,
    productId: Int,
    navigateToCart: () -> Unit,
    navigateBack: () -> Unit
) {
    homeViewModel.getProduct(productId)
    val selectedProduct by remember { homeViewModel.selectedProduct }
    var showBottomSheet by remember { mutableStateOf(false) }

    if (selectedProduct != null) {
        Scaffold(
            topBar = {
                DetailTopBar(
                    homeViewModel = homeViewModel,
                    isDetailScreen = true,
                    title = "Detail product",
                    navigateToCart = { navigateToCart() },
                    navigateBack = { navigateBack() }
                )
            },
            bottomBar = {
                HorizontalDivider(
                    color = GrayLighter
                )
                DetailBottomBar(
                    product = selectedProduct!!,
                    homeViewModel = homeViewModel
                ) {
                    showBottomSheet = true
                }
            },
            containerColor = Color.White
        ) { contentPadding ->
            Column(
                modifier = Modifier.padding(contentPadding)
            ) {
                //vertical scrollable part
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    // img slider
                    ImageBox(selectedProduct!!)
                    // all text
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 9.dp),
                    ) {
                        // title & price & wish
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                            ) {
                                // title
                                Text(
                                    text = selectedProduct!!.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    lineHeight = 19.sp,
                                    color = GrayDark
                                )
                                // price
                                Text(
                                    modifier = Modifier
                                        .padding(top = 6.dp),
                                    text = homeViewModel.getConvertedPrice(selectedProduct!!.price),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(500),
                                    lineHeight = 22.sp,
                                    color = GrayDark
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(GrayLighter, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    imageVector = ImageVector.vectorResource(id =
                                    if (wishlistViewModel.isFavoriteChecked(selectedProduct!!)) R.drawable.heart_fill
                                    else R.drawable.heart),
                                    contentDescription = null,
                                    modifier = Modifier.size(35.dp)
                                        .clickable {
                                            wishlistViewModel.toggleFavorite(selectedProduct!!)
                                        },
                                )
                            }

                        }
                        Text(
                            modifier = Modifier.padding(top = 15.dp),
                            text = "Description of product",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            lineHeight = 19.sp,
                            color = GrayDark
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 9.dp),
                            text = selectedProduct!!.description,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 21.sp,
                            color = GrayDark,
                        )
                    }
                }

                if (showBottomSheet) {
                    PaymentBottomSheet {
                        showBottomSheet = it
                    }
                }
            }
        }
    } else {
        NoResultBox(message = "Something went wrong")
    }
}