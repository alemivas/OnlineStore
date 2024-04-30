package com.example.presentation.common_item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Product
import com.example.presentation.R
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItem(
    homeViewModel: HomeViewModel,
    product: Product,
    navigateToDetail: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(product.id) },
        colors = CardDefaults.cardColors(containerColor = GrayLightest),
    ) {
        val pageCount = product.images.size
        val pagerState = rememberPagerState(pageCount = { pageCount })
        Box {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(112.dp),
                pageSpacing = 8.dp,
                state = pagerState
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model =
                    if (product.images.isEmpty()) R.drawable.img
                    else product.images[it].removePrefix("[\"").removeSuffix("\"]"),
                    contentDescription = null,
                )
            }
            DotsIndicator(
                dotCount = pageCount,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                type = ShiftIndicatorType(dotsGraphic = DotGraphic(size = 8.dp ,color = GrayLightest)),
                pagerState = pagerState
            )
        }
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
                text = homeViewModel.getConvertedPrice(product.price),
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                maxLines = 1,
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (homeViewModel.isContainsCart(product)) Red else Mint
                ),
                onClick = { homeViewModel.checkCart(product) }
            ) {
                Text(
                    text = if (homeViewModel.isContainsCart(product)) "Remove from cart" else "Add to cart",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                )
            }
        }
    }
}