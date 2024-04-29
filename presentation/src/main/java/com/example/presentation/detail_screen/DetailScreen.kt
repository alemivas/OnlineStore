package com.example.presentation.detail_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.detail_screen.common_item.DetailTopBar
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    homeViewModel: HomeViewModel,
    productId: Int,
    navigateToCart: () -> Unit,
    navigateBack: () -> Unit
) {
    homeViewModel.getProduct(productId)
    val selectedProduct by remember { homeViewModel.selectedProduct }

    if (selectedProduct != null) {
        Column {
            DetailTopBar(
                homeViewModel = homeViewModel,
                isDetailScreen = true,
                title = "Detail product",
                navigateToCart = { navigateToCart() },
                navigateBack = { navigateBack() }
            )
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                val pageCount = selectedProduct?.images?.size ?: 0
                val pagerState = rememberPagerState(pageCount = { pageCount })
                Box {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp),
                        pageSpacing = 24.dp,
                        state = pagerState
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(290.dp),
                            contentScale = ContentScale.Crop,
                            model = selectedProduct?.images?.get(it)?.removePrefix("[\"")?.removeSuffix("\"]"),
                            contentDescription = null,
                        )
                    }
                    DotsIndicator(
                        dotCount = pageCount,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp),
                        type = ShiftIndicatorType(dotsGraphic = DotGraphic(color = GrayLighter)),
                        pagerState = pagerState
                    )
                }

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
            }

            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider(color = GrayLighter)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp),
                    onClick = { homeViewModel.checkCart(selectedProduct!!) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (homeViewModel.isContainsCart(selectedProduct!!)) Red else Mint
                    ),
                ) {
                    Text(
                        text = if (homeViewModel.isContainsCart(selectedProduct!!)) "Remove from cart" else "Add to cart",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp),
                    onClick = { },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(GrayLighter),
                    border = BorderStroke(1.dp, GrayLight)
                ) {
                    Text(
                        text = "Buy Now",
                        color = GrayDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }
    } else {
        NoResultBox(message = "Something went wrong")
    }
}