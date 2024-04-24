package com.example.presentation.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.presentation.common_item.ProductItem
import com.example.presentation.home_screen.FilterProduct
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Red

//@Preview
@Composable
fun SearchScreen(
    navigateBack: () -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("j") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopSearchBar(
            searchQuery = searchQuery,
            newSearchQuery = { searchQuery = it },
            navigateBack = navigateBack
        )
        HorizontalDivider(color = GrayLighter)
        if (searchQuery.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilterProduct()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(10) {
                        ProductItem()
                    }
                }
            }
        } else {
            SearchResult()
        }
    }
}

@Composable
fun TopSearchBar(
    searchQuery: String,
    newSearchQuery: (String) -> Unit,
    navigateBack: () -> Unit,
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null,
            modifier = Modifier.clickable { navigateBack() },
            tint = GrayDark
        )
        OutlinedTextField(
            value = searchQuery,
            onValueChange = newSearchQuery,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    tint = Gray
                )
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search here ...",
                    fontWeight = FontWeight(400),
                    fontSize = 13.sp,
                    color = GrayLight,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedBorderColor = GrayLighter,
                unfocusedBorderColor = GrayLighter,
            )
        )
        Cart(count = 5) { }
    }
}

@Composable
fun SearchResult() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Last search",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = GrayDark,
            )
            Text(
                text = "Clear all",
                modifier = Modifier.clickable {  },
                fontWeight = FontWeight(500),
                fontSize = 12.sp,
                lineHeight = 15.sp,
                color = Red,
            )
        }
        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(10) {
                LastSearchRow()
            }
        }
    }
}

@Composable
fun LastSearchRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.time_circle),
            contentDescription = null,
            tint = Gray
        )
        Text(
            text = "Salatiga City, Central Java",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            lineHeight = 17.sp,
            color = GrayDark
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = Gray
        )
    }
}