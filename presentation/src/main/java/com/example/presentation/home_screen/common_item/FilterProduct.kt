package com.example.presentation.home_screen.common_item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint
import com.example.utils.Constants

@Composable
fun FilterProduct(
    homeViewModel: HomeViewModel,
    isHomeScreen: Boolean,
    sortedClicked: () -> Unit
) {
    var alertShow by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var priceMin by rememberSaveable { mutableStateOf("") }
    var priceMax by rememberSaveable { mutableStateOf("") }

    var nameFilterIsActive by rememberSaveable { mutableStateOf(false) }
    var nameFilterIncreaseOrder by rememberSaveable { mutableStateOf(true) }
    var priceFilterIsActive by rememberSaveable { mutableStateOf(false) }
    var priceFilterIncreaseOrder by rememberSaveable { mutableStateOf(true) }
    var priceRangeFilterIsActive by rememberSaveable { mutableStateOf(false) }

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
                Icon(
                    painter = painterResource(
                        id = R.drawable.filter
                    ),
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
                    text = { Text("By name") },
                    trailingIcon = {
                        if (nameFilterIsActive) {
                            Icon(
                                modifier =
                                    if (nameFilterIncreaseOrder)
                                        Modifier.graphicsLayer(scaleY = -1f)
                                    else
                                        Modifier
                                ,
                                painter = painterResource(id = R.drawable.baseline_sort_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }else
                            null
                    },
                    modifier = Modifier.background(if (nameFilterIsActive) Mint else Color.White),
                    colors = MenuDefaults.itemColors(if (nameFilterIsActive) Color.White else GrayDark),
                    onClick = {
                        expanded = false
                        nameFilterIncreaseOrder =
                            if (nameFilterIsActive)
                                !nameFilterIncreaseOrder
                            else
                                true
                        nameFilterIsActive = true
                        priceFilterIsActive = false
                        priceRangeFilterIsActive = false
                        homeViewModel.sortedProductList(
                            filter =
                                if (nameFilterIncreaseOrder)
                                    Constants.SortType.NAME
                                else
                                    Constants.SortType.REVERSE_NAME,
                            products =
                                if (isHomeScreen) homeViewModel.products.value.data ?: emptyList()
                                else homeViewModel.searchList.value.data ?: emptyList(),
                            priceMin = null,
                            priceMax = null
                        )
                        sortedClicked()
                    },
                )
                DropdownMenuItem(
                    text = { Text("By price") },
                    trailingIcon = {
                        if (priceFilterIsActive) {
                            Icon(
                                modifier =
                                if (priceFilterIncreaseOrder)
                                    Modifier.graphicsLayer(scaleY = -1f)
                                else
                                    Modifier
                                ,
                                painter = painterResource(id = R.drawable.baseline_sort_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }else
                            null
                    },
                    modifier = Modifier.background(if (priceFilterIsActive) Mint else Color.White),
                    colors = MenuDefaults.itemColors(if (priceFilterIsActive) Color.White else GrayDark),
                    onClick = {
                        expanded = false
                        priceFilterIncreaseOrder =
                            if (priceFilterIsActive)
                                !priceFilterIncreaseOrder
                            else
                                true
                        nameFilterIsActive = false
                        priceFilterIsActive = true
                        priceRangeFilterIsActive = false
                        homeViewModel.sortedProductList(
                            filter =
                                if (priceFilterIncreaseOrder)
                                    Constants.SortType.PRICE
                                else
                                    Constants.SortType.REVERSE_PRICE,
                            products =
                                if (isHomeScreen) homeViewModel.products.value.data ?: emptyList()
                                else homeViewModel.searchList.value.data ?: emptyList(),
                            priceMin = null,
                            priceMax = null
                        )
                        sortedClicked()
                    },
                )
                DropdownMenuItem(
                    text = { Text("Price range") },
                    modifier = Modifier.background(if (priceRangeFilterIsActive) Mint else Color.White),
                    colors = MenuDefaults.itemColors(if (priceRangeFilterIsActive) Color.White else GrayDark),
                    onClick = {
                        expanded = false
                        nameFilterIsActive = false
                        priceFilterIsActive = false
                        priceRangeFilterIsActive = true
                        homeViewModel.sortedProductList(
                            filter = Constants.SortType.RANGE,
                            products =
                                if (isHomeScreen) homeViewModel.products.value.data ?: emptyList()
                                else homeViewModel.searchList.value.data ?: emptyList(),
                            priceMin = priceMin.toIntOrNull() ?: 0,
                            priceMax = priceMax.toIntOrNull() ?: 100000
                        )
                        alertShow = true
                    },
                )
            }
        }
    }

    if (alertShow) {
        AlertDialog(
            onDismissRequest = { alertShow = false },
            title = { Text("Enter the price range") },
            text = {
                Column {
                    OutlinedTextField(
                        value = priceMin,
                        onValueChange = { priceMin = it },
                        label = { Text("Minimum price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Mint,
                            focusedBorderColor = Mint,
                            focusedLabelColor = Mint,
                            unfocusedBorderColor = Gray,
                            unfocusedTextColor = Gray,
                            unfocusedLabelColor = Gray
                        )
                    )
                    OutlinedTextField(
                        value = priceMax,
                        onValueChange = { priceMax = it },
                        label = { Text("Maximum price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Mint,
                            focusedBorderColor = Mint,
                            focusedLabelColor = Mint,
                            unfocusedBorderColor = Gray,
                            unfocusedTextColor = Gray,
                            unfocusedLabelColor = Gray
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (priceMin.isNotBlank() && priceMax.isNotBlank()) {
                            homeViewModel.sortedProductList(
                                filter = Constants.SortType.RANGE,
                                products = if (isHomeScreen) homeViewModel.products.value.data ?: emptyList()
                                else homeViewModel.searchList.value.data ?: emptyList(),
                                priceMin = homeViewModel.getBucksPrice (priceMin.toInt()),
                                priceMax = homeViewModel.getBucksPrice (priceMax.toInt())
                            )
                            sortedClicked()
                            alertShow = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(Mint),
                ) {
                    Text("Apply")
                }
            },
            containerColor = GrayLightest
        )
    }
}