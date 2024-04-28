package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.account_screen.AccountScreen
import com.example.presentation.detail_screen.DetailScreen
import com.example.presentation.home_screen.HomeScreen
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.login_screen.LoginScreen
import com.example.presentation.login_screen.RegistrationScreen
import com.example.presentation.manager_screen.ManagerScreen
import com.example.presentation.search_screen.SearchScreen
import com.example.presentation.wishlist_screen.WishlistScreen

@Composable
fun Navigation(navController: NavHostController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                homeViewModel = homeViewModel,
                navigationToSearchScreen = {navController.navigate(NavigationObject.SearchScreen.route)},
                navigationToDetailScreen = { navController.navigate(NavigationObject.DetailScreen.route) },
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
                homeViewModel = homeViewModel,
                navigateBack = { navController.popBackStack() },
                navigationToDetailScreen = { navController.navigate(NavigationObject.DetailScreen.route) },
            )
        }

        composable(NavigationObject.LoginScreen.route) {
            LoginScreen(
                navigateToRegistration = { navController.navigate(NavigationObject.RegistrationScreen.route) },
                navigateToHome = { navController.navigate(NavigationItem.Home.route) }
            )
        }

        composable(NavigationObject.RegistrationScreen.route) {
            RegistrationScreen(
                navigateToHome = { navController.navigate(NavigationItem.Home.route) },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(NavigationObject.DetailScreen.route) {
            DetailScreen()
        }
    }
}