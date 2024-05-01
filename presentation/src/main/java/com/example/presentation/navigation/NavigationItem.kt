package com.example.presentation.navigation

import com.example.presentation.R

sealed class NavigationItem(val title: String, val route: String, val icon: Int, val iconFill: Int) {
    data object Home : NavigationItem("Home", "homeScreen", R.drawable.home, R.drawable.home_fill)
    data object Wishlist : NavigationItem("Wishlist", "wishlistScreen", R.drawable.heart, R.drawable.heart_fill)
    data object Manager : NavigationItem("Manager", "managerScreen", R.drawable.paper, R.drawable.paper_fill)
    data object Account : NavigationItem("Account", "accountScreen", R.drawable.profile, R.drawable.profile_fill)
}

sealed class NavigationObject(val route: String) {
    data object SearchScreen : NavigationObject("SearchScreen")
    data object LoginScreen : NavigationObject("LoginScreen")
    data object RegistrationScreen : NavigationObject("RegistrationScreen")
    data object DetailScreen : NavigationObject("DetailScreen") {
        fun createRoute(productId: Int) = "$route/$productId"
    }
    data object ShoppingCartScreen: NavigationObject("ShoppingCartScreen")
}