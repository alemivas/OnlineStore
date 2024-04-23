package com.example.presentation.common_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryItem(
    category: String,
    categoryImage: Int,
) {
    Column(
        modifier = Modifier.background(Color.White)
            .clickable {  },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp)
                .padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFE9FFF8), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(
                    id = categoryImage),
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp),
                    tint = Color(0xFF67C4A7))
            }
        }
        Text(
            text = category,
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF939393)
        )
    }
}