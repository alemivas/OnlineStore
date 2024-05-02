@file:OptIn(ExperimentalFoundationApi::class)
package com.example.presentation.onboarding_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navigateToLoginScreen: () -> Unit,
) {
    val state = rememberPagerState(pageCount = { images.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorPage(state)
        Text(text = description,
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp))
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp, start = 15.dp, end = 15.dp),
            contentAlignment = Alignment.BottomEnd){
            Row {
                DotsIndicator(totalDots = state.pageCount, selectedIndex = state.currentPage )
            }
                if(state.currentPage == 2){
                    IconButton(onClick = {
                        navigateToLoginScreen()
                    }, colors = IconButtonColors(
                        Color.Black, Color.White,
                        Color.Black, Color.White),
                        modifier = Modifier
                            .size(72.dp)
                            .padding(8.dp),
                    ) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "sd")
                    }
            }
        }
    }

}

@Composable
fun HorPage(state: PagerState){

    HorizontalPager(state = state) { page ->
        Column(
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            Box(Modifier.fillMaxWidth(),contentAlignment = Alignment.TopCenter) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.55f)
                        .padding(15.dp)
                        .clip(
                            RoundedCornerShape(
                                topEndPercent = 10,
                                topStartPercent = 10,
                                bottomStartPercent = 20,
                                bottomEndPercent = 40
                            )
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Text(text = text[page],
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp));
        }
    }
}
@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Start
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(24.dp, 6.dp)
                        .clip(CircleShape)
                        .background(color = Color.Black)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview(){
    OnboardingScreen {}
}
val images = listOf(
    R.drawable.onboard1,
    R.drawable.onboard2,
    R.drawable.onboard3
)
val text = listOf(
    "20% Discount \nNew Arrival Product",
    "Take Advantage \nOf The Offer Shopping",
    "All Types Offer \nWithin Your Reach"
)
val description = "Publish up your selfies to make yourself more beautiful with this app."