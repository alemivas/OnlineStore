package com.example.presentation.navigation

import com.example.presentation.R

sealed class NavigationItem(val title: String, val route: String, var icon: Int) {
    data object Home : NavigationItem("Home", "homeScreen", R.drawable.home)
    data object Wishlist : NavigationItem("Wishlist", "wishlistScreen", R.drawable.heart)
    data object Manager : NavigationItem("Manager", "managerScreen", R.drawable.paper)
    data object Account : NavigationItem("Account", "accountScreen", R.drawable.profile)
}
