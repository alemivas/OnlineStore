package com.example.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.common_item.Cart
import com.example.presentation.common_item.CategoryItem
import com.example.presentation.common_item.ProductItem
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.GrayLightest

val categoryList = listOf("Laptop", "Smartphone", "Tablet", "All")

@Composable
fun HomeScreen(
    navigationToSearchScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TopBar(navigationToSearchScreen)
        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categoryList.size) { category ->
                CategoryItem(categoryList[category], R.drawable.category)
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(10) {
                ProductItem()
            }
        }
    }
}

@Composable
fun TopBar(
    navigationToSearchScreen: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Delivery address",
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = GrayLight,
            )
            Row(
                modifier = Modifier.clickable { expanded = true }
            ) {
                Text(
                    text = "Salatiga City, Central Java",
                    color = GrayDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = GrayDark
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(GrayLightest)
            ) {
                repeat(10) {
                    DropdownMenuItem(
                        text = { Text("Item ${it + 1}") },
                        onClick = { /* TODO */ },
                    )
                }
            }
        }
        Cart(count = 5) { }
        Icon(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = null,
            modifier = Modifier.padding(10.dp),
            tint = GrayDark
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .border(1.dp, GrayLighter, RoundedCornerShape(8.dp))
            .clickable { navigationToSearchScreen() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            modifier = Modifier.padding(10.dp),
            tint = Color(0xFF939393)
        )
        Text(
            text = "Search here ...",
            fontWeight = FontWeight(400),
            fontSize = 13.sp,
            color = GrayLight,
        )
    }
}

@Composable
fun FilterProduct() {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(27.dp)
            .padding(start = 6.dp, end = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Product",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = GrayDark
        )
        Column {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .border(
                        1.dp, GrayLighter,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(horizontal = 8.dp)
                    .clickable { expanded = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Filter",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = GrayDark
                )
                Icon(painter = painterResource(
                    id = R.drawable.filter),
                    contentDescription = null,
                    tint = GrayDark
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(GrayLightest),
            ) {
                DropdownMenuItem(
                    text = { Text("по названию") },
                    onClick = { expanded = false },
                )
                DropdownMenuItem(
                    text = { Text("по цене") },
                    onClick = { expanded = false },
                )
                DropdownMenuItem(
                    text = { Text("ценовой диапазон") },
                    onClick = { expanded = false },
                )
            }
        }
    }
}