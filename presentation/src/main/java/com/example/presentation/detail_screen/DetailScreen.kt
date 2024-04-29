package com.example.presentation.detail_screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.common_item.PaymentBottomSheet
import com.example.presentation.detail_screen.common_item.BottomBarDetail
import com.example.presentation.detail_screen.common_item.DetailTopBar
import com.example.presentation.detail_screen.common_item.ImageBox
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter

@Composable
fun DetailScreen(
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
                HorizontalDivider(color = GrayLighter)
                BottomBarDetail(
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
                ImageBox(selectedProduct!!)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = selectedProduct!!.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                color = GrayDark
                            )
                            Text(
                                text = homeViewModel.getConvertedPrice(selectedProduct!!.price),
                                fontSize = 18.sp,
                                fontWeight = FontWeight(500),
                                color = GrayDark
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .background(GrayLighter, CircleShape)
                                .clickable { homeViewModel.toggleFavorite(selectedProduct!!) },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id =
                                if (homeViewModel.isFavoriteChecked(selectedProduct!!)) R.drawable.heart_fill
                                else R.drawable.heart),
                                contentDescription = null,
                            )
                        }
                    }
                    Text(
                        text = "Description of product",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = GrayDark
                    )
                    Text(
                        text = selectedProduct!!.description,
                        fontSize = 12.sp,
                        color = GrayDark,
                    )
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