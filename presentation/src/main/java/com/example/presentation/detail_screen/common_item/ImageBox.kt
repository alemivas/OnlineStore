package com.example.presentation.detail_screen.common_item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.models.Product
import com.example.presentation.theme.GrayLighter
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageBox(
    product: Product,
) {
    val pageCount = product.images.size
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
                model = product.images[it].removePrefix("[\"").removeSuffix("\"]"),
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
}