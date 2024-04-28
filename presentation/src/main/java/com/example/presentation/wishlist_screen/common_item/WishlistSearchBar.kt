package com.example.presentation.wishlist_screen.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.common_item.Cart
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.wishlist_screen.WishlistViewModel

@Composable
fun WishlistSearchBar(
    wishlistViewModel: WishlistViewModel,
    padding: Dp,
    navigateToCart: () -> Unit,
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = wishlistViewModel.searchQuery.value,
            onValueChange = { wishlistViewModel.changeSearchQuery(it) },
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
            ),
            keyboardActions = KeyboardActions(onSearch = {
                wishlistViewModel.getSearchFavoriteList()
            }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
        )
        Cart(wishlistViewModel.cart.value.size) { navigateToCart() }
    }
}