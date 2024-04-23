package com.example.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    var selectedItem by remember {
        mutableIntStateOf(
            when (currentRoute) {
                NavigationItem.Home.route -> 0
                NavigationItem.Wishlist.route -> 1
                NavigationItem.Manager.route -> 2
                NavigationItem.Account.route -> 3
                else -> 0
            }
        )
    }

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Wishlist,
        NavigationItem.Manager,
        NavigationItem.Account
    )
    NavigationBar(
        containerColor = Color.White,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon),contentDescription = item.title) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF67C4A7),
                    selectedTextColor = Color(0xFF67C4A7),
                    unselectedIconColor = Color(0xFF939393),
                    unselectedTextColor = Color(0xFF939393),
                    indicatorColor = Color.White
                ),
                label = {
                    Text(text = item.title)
                },
                onClick = {
                    if (selectedItem != index) {
                        selectedItem = index
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}