package com.example.presentation.common_item

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.home_screen.SortType
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint

@Composable
fun FilterProduct(
    homeViewModel: HomeViewModel,
    sortedClicked: () -> Unit
) {
    var alertShow by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var priceMin by rememberSaveable { mutableStateOf("") }
    var priceMax by rememberSaveable { mutableStateOf("") }

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
                    text = { Text("По названию") },
                    onClick = {
                        expanded = false
                        homeViewModel.sortedProductList(
                            filter = SortType.NAME,
                            products = homeViewModel.products.value.data ?: emptyList(),
                            priceMin = null,
                            priceMax = null
                        )
                        sortedClicked()
                    },
                )
                DropdownMenuItem(
                    text = { Text("По цене") },
                    onClick = {
                        expanded = false
                        homeViewModel.sortedProductList(
                            filter = SortType.PRICE,
                            products = homeViewModel.products.value.data ?: emptyList(),
                            priceMin = null,
                            priceMax = null
                        )
                        sortedClicked()
                    },
                )
                DropdownMenuItem(
                    text = { Text("Ценовой диапазон") },
                    onClick = {
                        expanded = false
                        homeViewModel.sortedProductList(
                            filter = SortType.RANGE,
                            products = homeViewModel.products.value.data ?: emptyList(),
                            priceMin = 15,
                            priceMax = 33
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
            title = { Text("Введите ценовой диапазон") },
            text = {
                Column {
                    OutlinedTextField(
                        value = priceMin,
                        onValueChange = { priceMin = it },
                        label = { Text("Минимальная цена") },
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
                        label = { Text("Максимальная цена") },
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
                                filter = SortType.RANGE,
                                products = homeViewModel.products.value.data ?: emptyList(),
                                priceMin = priceMin.toInt(),
                                priceMax = priceMax.toInt()
                            )
                            sortedClicked()
                            alertShow = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(Mint),
                ) {
                    Text("Применить")
                }
            },
            containerColor = GrayLightest
        )
    }
}