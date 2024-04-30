package com.example.presentation.shopping_cart_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.common_item.Cart
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.GrayLightest
import com.example.presentation.theme.Mint


@Preview(showBackground = true)
@Composable
fun Prev() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    ShoppingCart(homeViewModel, navigateBack =  {})
}

@Composable
fun ShoppingCart(
    homeViewModel: HomeViewModel,
    navigateBack: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable { navigateBack() },
                tint = GrayDark
            )
            Text(
                text = "Your Cart",
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.width(180.dp))
            Cart(homeViewModel.cart.value.size)
            {}
        }
        HorizontalDivider(color = GrayLighter)
        Row(
            modifier = Modifier.clickable { expanded = true },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Delivery to",
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.width(120.dp))
            Text(
                text = "Salatiga City, Central Java",
                color = GrayDark,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(top = 2.dp)
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
                repeat(10) {
                    DropdownMenuItem(
                        text = { Text("Item ${it + 1}") },
                        onClick = { /* TODO */ },
                    )
                }
            }
        }

        HorizontalDivider(color = GrayLighter)
        ColumnOfCart()

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,

        ) {
            HorizontalDivider(color = GrayLighter)
            Column(
                modifier = Modifier
                    .fillMaxWidth(constraints.maxWidth.toFloat())
                ,

            ) {
                HorizontalDivider(color = GrayLighter)
                Text(
                    text = "Order Summary",
                    color = GrayDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(start = 7.dp, top = 11.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, bottom = 110.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = "Totals",
                        color = GrayDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                    )
                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = "$ 123123",
                        color = GrayDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                    )
                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(constraints.maxWidth.toFloat())
                    .padding(bottom = 40.dp)
                    .height(50.dp),
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Mint),
            ) {
                Text(
                    text = "Select payment method",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun CheckboxWithColor() {
    // Состояние для отслеживания выбранного состояния
    val checkedState = remember { mutableStateOf(false) }

    // Цвет фона в зависимости от состояния Checkbox
    val backgroundColor = if (checkedState.value) Color(0xFF03DAC5) else Color.Transparent

    // Отрисовка Checkbox с возможностью изменения цвета фона и состояния
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Transparent, // Прозрачный цвет при активации
            uncheckedColor = Color.Transparent // Прозрачный цвет при деактивации
        ),
        modifier = Modifier
            .size(24.dp) // Размер Checkbox
            .background(color = backgroundColor) // Цвет фона Checkbox
    )
}


@Composable
fun ColumnOfCart() {
    LazyColumn(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CheckboxWithColor()
                Product()

            }
        }
    }
}

@Composable
fun Product() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .height(112.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = "Monitor LG 22”inc 4K 120Fps",
                color = GrayDark,
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                maxLines = 1,

                )
            Row {
                Text(
                    text = "$199.99",
                    color = GrayDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    maxLines = 1,
                    modifier = Modifier.padding(top = 50.dp, start = 8.dp)
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(top = 33.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_remove_circle_24),
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp),
                        tint = Gray
                    )
                }
                Text(
                    text = "1", color = GrayDark,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    maxLines = 1,
                    modifier = Modifier.padding(top = 48.dp)
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(top = 33.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_circle_24),
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp),
                        tint = Gray
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(top = 33.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = null,
                        modifier = Modifier.padding(2.dp),
                        tint = Gray
                    )
                }
            }

        }
    }
}
