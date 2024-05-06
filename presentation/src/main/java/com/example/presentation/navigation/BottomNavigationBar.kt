package com.example.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.presentation.main_screen.MainViewModel

@Composable
fun BottomNavigationBar(
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val isManager by mainViewModel.isManager.collectAsState()

    val screens = listOf(
        NavigationItem.Home,
        NavigationItem.Wishlist,
        NavigationItem.Manager,
        NavigationItem.Account
    )

    NavigationBar(
        containerColor = Color.White,
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        screens.forEach { screen ->
            if (screen.route != NavigationItem.Manager.route || isManager) {
                val isSelected = screen.route == currentRoute
                NavigationBarItem(
                    icon = {
                        if (isSelected) {
                            Image(
                                imageVector = ImageVector.vectorResource(screen.iconFill),
                                contentDescription = null
                            )
                        } else {
                            Image(
                                imageVector = ImageVector.vectorResource(screen.icon),
                                contentDescription = null
                            )
                        }
                    },
                    selected = isSelected,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF67C4A7),
                        selectedTextColor = Color(0xFF67C4A7),
                        unselectedIconColor = Color(0xFF939393),
                        unselectedTextColor = Color(0xFF939393),
                        indicatorColor = Color.Transparent
                    ),
                    label = {
                        Text(text = screen.title)
                    },
                    onClick = {
                        navController.navigate(screen.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}