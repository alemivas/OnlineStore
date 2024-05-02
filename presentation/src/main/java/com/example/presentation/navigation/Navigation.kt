package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.presentation.account_screen.AccountScreen
import com.example.presentation.detail_screen.DetailScreen
import com.example.presentation.home_screen.HomeScreen
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.login_screen.LoginScreen
import com.example.presentation.login_screen.RegistrationScreen
import com.example.presentation.manager_screen.ManagerScreen
import com.example.presentation.navigation.NavigationObject.Companion.PRODUCT_ID_PARAM_KEY
import com.example.presentation.onboarding_screen.OnboardingScreen
import com.example.presentation.search_screen.SearchScreen
import com.example.presentation.shopping_cart_screen.ShoppingCart
import com.example.presentation.wishlist_screen.WishlistScreen

@Composable
fun Navigation(navController: NavHostController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(navController, startDestination = NavigationObject.LoginScreen.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                homeViewModel = homeViewModel,
                navigateToSearch = {navController.navigate(NavigationObject.SearchScreen.route)},
                navigateToCart = { navController.navigate(NavigationObject.ShoppingCartScreen.route) },
                navigateToDetail = { productId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(productId)) {
                        popUpTo(NavigationItem.Home.route)
                    }
                }
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
                navigateToDetail = { productId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(productId)) {
                        popUpTo(NavigationObject.SearchScreen.route)
                    }
                },
                navigateToCart = { navController.navigate(NavigationObject.ShoppingCartScreen.route) },
                navigateBack = { navController.navigateUp() }
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
                navigateToOnboarding = { navController.navigate(NavigationObject.OnBoardingScreen.route) },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(NavigationObject.OnBoardingScreen.route) {
            OnboardingScreen {
                navController.navigate(NavigationItem.Home.route)
            }
        }

        composable(
            route = "${NavigationObject.DetailScreen.route}/{$PRODUCT_ID_PARAM_KEY}",
            arguments = listOf(navArgument(PRODUCT_ID_PARAM_KEY) { type = NavType.IntType })
        ) {
            val productId =
                it.arguments?.getInt(PRODUCT_ID_PARAM_KEY) ?: throw IllegalStateException()
            DetailScreen(
                homeViewModel = homeViewModel,
                productId = productId,
                navigateToCart = { navController.navigate(NavigationObject.ShoppingCartScreen.route) },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(NavigationObject.ShoppingCartScreen.route) {
            ShoppingCart(
                homeViewModel = homeViewModel,
            navigateToDetail = { productId ->
                navController.navigate(NavigationObject.DetailScreen.createRoute(productId)) {
                    popUpTo(NavigationObject.ShoppingCartScreen.route)
                }
            },
            navigateBack = { navController.navigateUp() }
            )
        }
    }
}