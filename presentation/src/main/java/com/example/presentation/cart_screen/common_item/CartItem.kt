package com.example.presentation.cart_screen.common_item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.models.Cart
import com.example.presentation.R
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.theme.GrayDarkest
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Mint

@Composable
fun CartItem(
    homeViewModel: HomeViewModel,
    cart: Cart,
    navigateToDetail: (Int) -> Unit,
    ) {
    val checkedState = homeViewModel.checkedStates(cart)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(80.dp)
            .clickable { navigateToDetail(cart.product.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { homeViewModel.changeCheckedProducts(cart) },
            colors = CheckboxDefaults.colors(
                checkedColor = Mint,
                uncheckedColor = GrayLighter)
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            model = cart.product.images.first().removePrefix("[\"").removeSuffix("\"]"),
            contentDescription = null,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = cart.product.title,
                color = GrayDarkest
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = homeViewModel.getConvertedPrice(cart.product.price),
                    modifier = Modifier.weight(1f),
                    color = GrayDarkest
                )
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = null,
                    modifier = Modifier.border(1.dp, GrayLighter, CircleShape)
                        .clickable { homeViewModel.removeFromCart(cart) },
                    tint = GrayLight
                )
                Text(
                    text = cart.quantity.toString(),
                    color = GrayDarkest
                )
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.border(1.dp, GrayLighter, CircleShape)
                        .clickable { homeViewModel.addToCart(cart) },
                    tint = GrayLight
                )
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    modifier = Modifier.border(1.dp, GrayLighter, CircleShape)
                        .clickable { homeViewModel.deleteFromCart(cart) },
                    tint = GrayLight
                )
            }
        }
    }
}