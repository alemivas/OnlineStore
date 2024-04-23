package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.account_screen.AccountScreen
import com.example.presentation.home_screen.HomeScreen
import com.example.presentation.manager_screen.ManagerScreen
import com.example.presentation.search_screen.SearchScreen
import com.example.presentation.wishlist_screen.WishlistScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                navigationToSearchScreen = {navController.navigate(NavigationObject.SearchScreen.route)}
            )
        }

        composable(NavigationItem.Wishlist.route) {
            WishlistScreen()
        }

        composable(NavigationItem.Manager.route) {
            ManagerScreen()
        }

        composable(NavigationItem.Account.route) {
            AccountScreen()
        }

        composable(NavigationObject.SearchScreen.route) {
            SearchScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}