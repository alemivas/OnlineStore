package com.example.presentation.home_screen.common_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.models.Category
import com.example.presentation.R
import com.example.presentation.theme.Gray
import com.example.presentation.theme.MintLight

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .size(40.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                model = category.image,
                contentDescription = null
            )
        }
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = Gray
        )
    }
}

@Composable
fun CategoryItemAll(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .size(40.dp)
                .background(MintLight)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(
                id = R.drawable.category),
                contentDescription = null,
            )
        }
        Text(
            text = "All",
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = Gray
        )
    }
}