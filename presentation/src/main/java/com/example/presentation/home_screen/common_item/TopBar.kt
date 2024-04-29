package com.example.presentation.home_screen.common_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.common_item.Cart
import com.example.presentation.home_screen.Country
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLightest

@Composable
fun TopBar(
    homeViewModel: HomeViewModel,
    navigationToCartScreen: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val countryList = Country.entries.map { it.toString() }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Delivery country",
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = GrayLight,
            )
            Row(
                modifier = Modifier.clickable { expanded = true }
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
            }
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
        Cart(homeViewModel.cart.value.size) { navigationToCartScreen() }
        Icon(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = null,
            modifier = Modifier.padding(10.dp),
            tint = GrayDark
        )
    }
}