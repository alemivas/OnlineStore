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
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.home_screen.common_item.ProductVerticalGrid
import com.example.presentation.home_screen.common_item.SearchBar
import com.example.presentation.theme.GrayLighter

@Composable
fun WishlistScreen(
    homeViewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
) {
    val favoriteList = homeViewModel.favoriteList.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        SearchBar(
            homeViewModel = homeViewModel,
            isSearchScreen = false,
            padding = 16.dp,
            navigateToSearch = {},
            navigateBack = {}
        )
        HorizontalDivider(color = GrayLighter)

        if (homeViewModel.searchFavoriteQuery.value.isEmpty()) {
            if (favoriteList.isEmpty()) NoResultBox("Add Favorite")
            else ProductVerticalGrid(homeViewModel, favoriteList, true) { navigateToDetail(it) }
        } else {
            ProductVerticalGrid(homeViewModel, homeViewModel.getSearchFavoriteList(), true) {
                navigateToDetail(it)
            }
        }
    }
}