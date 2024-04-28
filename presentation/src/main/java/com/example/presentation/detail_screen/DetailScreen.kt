package com.example.presentation.detail_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint

@Composable
fun DetailScreen(

){
    Column(

    ) {
        //vertical scrollable part
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)     //чтобы "закрепить" кнопки снизу
        ){
            // img slider
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                Image(
                    modifier = Modifier
                        .height(286.dp)
                        .width(screenWidth),
                    painter = painterResource(id = R.drawable.imgslider1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    )
                Image(
                    modifier = Modifier
                        .height(286.dp)
                        .width(screenWidth),
                    painter = painterResource(id = R.drawable.imgslider2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier
                        .height(286.dp)
                        .width(screenWidth),
                    painter = painterResource(id = R.drawable.imgslider3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
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
                        //TODO
    //                    Text(text = "Air pods max by Apple Air pods max by AppleAir pods max by Apple")
                        Text(
                            text = "Air pods max by Apple",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            lineHeight = 19.sp,
                            color = GrayDark
                        )

                        // cost
                        Text(
                            modifier = Modifier
                                .padding(top = 6.dp),
                            text = "\$ 1999,99",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            lineHeight = 22.sp,
                            color = GrayDark
                        )
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
                Text(

                    modifier = Modifier
                        .padding(top = 9.dp),
                    //region desc
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquet arcu id tincidunt tellus arcu rhoncus, turpis nisl sed. Neque viverra ipsum orci, morbi semper. Nulla bibendum purus tempor semper purus. Ut curabitur platea sed blandit. Amet non at proin justo nulla et. A, blandit morbi suspendisse vel malesuada purus massa mi. Faucibus neque a mi hendrerit.\n" +
                        "\n" +
                        "Audio Technology\n" +
                        "Apple-designed dynamic driver\n" +
                        "Active Noise Cancellation\n" +
                        "Transparency mode\n" +
                        "Adaptive EQ\n" +
                        "Spatial audio with dynamic head tracking1\n" +
                        "\n" +
                        "Sensors\n" +
                        "Optical sensor (each ear cup)\n" +
                        "Position sensor (each ear cup)\n" +
                        "Case-detect sensor (each ear cup)\n" +
                        "Accelerometer (each ear cup)\n" +
                        "Gyroscope (left ear cup)\n" +
                        "\n" +
                        "Microphones\n" +
                        "Nine microphones total:\n" +
                        "Eight microphones for Active Noise Cancellation\n" +
                        "Three microphones for voice pickup (two shared with Active Noise Cancellation and one additional microphone)\n" +
                        "\n" +
                        "Chip\n" +
                        "Apple H1 headphone chip (each ear cup)\n" +
                        "\n" +
                        "Controls\n" +
                        "Digital Crown\n" +
                        "Turn for volume control\n" +
                        "Press once to play, pause or answer a phone call\n" +
                        "Press twice to skip forwards\n" +
                        "Press three times to skip back\n" +
                        "Press and hold for Siri\n" +
                        "Noise control button\n" +
                        "Press to switch between Active Noise Cancellation and Transparency mode\n" +
                        "\n" +
                        "Size and Weight2\n" +
                        "AirPods Max, including cushions\n" +
                        "Weight: 384.8g\n" +
                        "\n" +
                        "Smart Case\n" +
                        "Weight: 134.5g\n" +
                        "\n" +
                        "Battery\n" +
                        "AirPods Max\n" +
                        "Up to 20 hours of listening time on a single charge with Active Noise Cancellation or Transparency mode enabled3\n" +
                        "Up to 20 hours of movie playback on a single charge with spatial audio on4\n" +
                        "Up to 20 hours of talk time on a single charge5\n" +
                        "5 minutes of charge time provides around 1.5 hours of listening time6\n" +
                        "AirPods Max with Smart Case\n" +
                        "\n" +
                        "Storage in the Smart Case preserves battery charge in ultra-low-power state\n" +
                        "Charging via Lightning connector\n" +
                        "\n" +
                        "Connectivity\n" +
                        "Bluetooth 5.0\n" +
                        "\n" +
                        "In the Box\n" +
                        "AirPods Max\n" +
                        "Smart Case\n" +
                        "Lightning to USB-C Cable\n" +
                        "Documentation\n" +
                        "Accessibility7\n" +
                        "Accessibility features help people with disabilities get the most out of their new AirPods Max.\n" +
                        "\n" +
                        "Features include:\n" +
                        "Live Listen audio\n" +
                        "Headphone levels\n" +
                        "Headphone Accommodations\n" +
                        "\n" +
                        "System Requirements8\n" +
                        "iPhone and iPod touch models with the latest version of iOS\n" +
                        "iPad models with the latest version of iPadOS\n" +
                        "Apple Watch models with the latest version of watchOS\n" +
                        "Mac models with the latest version of macOS\n" +
                        "Apple TV models with the latest version of tvOS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 21.sp,
                    color = GrayDark
                    // endregion
                )
            }
        }

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