package com.example.presentation.detail_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint

@Composable
fun DetailScreen(
    homeViewModel: HomeViewModel
){
//    val product = homeViewModel.getProduct()!!


    Column(
        modifier = Modifier,
    ) {
        //vertical scrollable part
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = true),     //чтобы "закрепить" кнопки снизу, даже если мало описания
        ){
            // img slider
            val screenWidth = LocalConfiguration.current.screenWidthDp
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
//                product.images.forEach { image ->
//                    AsyncImage(
//                        modifier = Modifier
//                            .height(286.dp)
//                            .width(screenWidth.dp),
//                        contentScale = ContentScale.Crop,
//                        model = image.removePrefix("[\"").removeSuffix("\"]"),
//                        contentDescription = null,
//                    )
//                }
            }

            // all text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 9.dp)

            ) {
                // title & cost & wish
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(

                    ) {
                        // title
//                        Text(
//                            modifier = Modifier
//                                .width((screenWidth-102).dp),
//                            text = product.title,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.W500,
//                            lineHeight = 19.sp,
//                            color = GrayDark
//                        )

                        // cost
//                        Text(
//                            modifier = Modifier
//                                .padding(top = 6.dp),
//                            text = "$${product.price}",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.W500,
//                            lineHeight = 22.sp,
//                            color = GrayDark
//                        )
                    }
                    // wish
                    IconButton(
                        modifier = Modifier
                            .size(46.dp),
                        onClick = {  },
                        colors = IconButtonDefaults.iconButtonColors(GrayLighter),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(22.dp),
                            painter = painterResource(id = R.drawable.heart),
                            tint = Gray,
                            contentDescription = null
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "Description of product",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 19.sp,
                    color = GrayDark
                )
//                Text(
//
//                    modifier = Modifier
//                        .padding(top = 9.dp),
//                    text = product.description,
//                    fontSize = 12.sp,
//                    fontWeight = FontWeight.W400,
//                    lineHeight = 21.sp,
//                    color = GrayDark
//                )
            }
        }

        Column(
        ) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 16.dp),
                thickness = 1.dp,
                color = GrayLighter
            )

            //bottom button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp/*, top = 9.dp*/)
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 14.dp, end = 7.dp)
                        .height(45.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(Mint),
                    onClick = {  }
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Add to cart",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 17.sp,
                        color = Color.White
                    )
                }

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 14.dp, start = 7.dp)
                        .height(45.dp),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, GrayLight),
                    colors = ButtonDefaults.buttonColors(GrayLighter),
                    onClick = {  }
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Buy Now",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 17.sp,
                        color = GrayDark
                    )
                }
            }
        }

    }
}