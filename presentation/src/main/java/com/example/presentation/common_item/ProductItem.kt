package com.example.presentation.common_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Preview
@Composable
fun ProductItem() {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable {  },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFC)),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(13.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Monitor LG 22”inc 4K 120Fps",
                color = Color(0xFF393F42),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                maxLines = 1,
            )
            Text(
                text = "$199.99",
                color = Color(0xFF393F42),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                maxLines = 1,
            )
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 6.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF67C4A7)
                ),
                onClick = { /*TODO*/ }
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