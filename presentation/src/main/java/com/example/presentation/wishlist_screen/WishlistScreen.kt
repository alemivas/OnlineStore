package com.example.presentation.wishlist_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.theme.GrayLighter
import com.example.presentation.wishlist_screen.common_item.FavoriteVerticalGrid
import com.example.presentation.wishlist_screen.common_item.WishlistSearchBar

@Composable
fun WishlistScreen(
    wishlistViewModel: WishlistViewModel,
    navigateToDetail: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        WishlistSearchBar(
            wishlistViewModel = wishlistViewModel,
            padding = 16.dp,
        )
        HorizontalDivider(color = GrayLighter)

        if (wishlistViewModel.searchQuery.value.isEmpty()) {
            if (wishlistViewModel.favoriteList.value.isEmpty()) NoResultBox("Add Favorite")
            else FavoriteVerticalGrid(wishlistViewModel, wishlistViewModel.favoriteList.value) { navigateToDetail(it) }
        } else {
            val listProduct = wishlistViewModel.getSearchFavoriteList()
            FavoriteVerticalGrid(wishlistViewModel, listProduct) { navigateToDetail(it) }
        }
    }
}