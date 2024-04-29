package com.example.presentation.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.cart_screen.common_item.BottomBarCart
import com.example.presentation.cart_screen.common_item.CartItem
import com.example.presentation.common_item.PaymentBottomSheet
import com.example.presentation.detail_screen.common_item.DetailTopBar
import com.example.presentation.home_screen.Country
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.GrayLightest

@Composable
fun CartScreen(
    homeViewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val countryList = Country.entries.map { it.toString() }

    Scaffold(
        topBar = {
            DetailTopBar(
                homeViewModel = homeViewModel,
                isDetailScreen = false,
                title = "Your Cart",
                navigateToCart = {},
                navigateBack = { navigateBack() }
            )
            HorizontalDivider(color = GrayLighter)
        },
        bottomBar = {
            HorizontalDivider(color = GrayLighter)
            BottomBarCart(homeViewModel = homeViewModel) {
                showBottomSheet = true
                homeViewModel.makeOrder()
            }
        },
        containerColor = Color.White
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Delivery to",
                    modifier = Modifier.weight(1f),
                    color = GrayDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )
                Row(
                    modifier = Modifier.clickable { expanded = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = homeViewModel.currentCountry.value,
                        color = GrayDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = GrayDark
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(GrayLightest)
                    ) {
                        repeat(countryList.size) {
                            DropdownMenuItem(
                                text = { Text(countryList[it]) },
                                onClick = {
                                    homeViewModel.changeCurrentCountry(countryList[it])
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }
            HorizontalDivider(color = GrayLighter)

            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(homeViewModel.cart.value.size) {
                    CartItem(
                        homeViewModel = homeViewModel,
                        cart = homeViewModel.cart.value[it],
                        navigateToDetail = navigateToDetail
                    )
                }
            }
            if (showBottomSheet) {
                PaymentBottomSheet {
                    showBottomSheet = it
                }
            }
        }
    }
}