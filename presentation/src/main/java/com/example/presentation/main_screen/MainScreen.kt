package com.example.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.BottomNavigationBar
import com.example.presentation.navigation.Navigation

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // Получаем данные о пользователе из MainViewModel
    val user by mainViewModel.user.collectAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "homeScreen" -> true
        "wishlistScreen" -> true
        "managerScreen" -> true
        "accountScreen" -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) user?.let { BottomNavigationBar(navController, it) }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)
                .background(Color.White)) {
                Navigation(
                    navController = navController
                )
            }
        },
    )
}