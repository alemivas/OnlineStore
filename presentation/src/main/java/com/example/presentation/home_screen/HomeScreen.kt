package com.example.presentation.home_screen

import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.common_item.ShimmerAnimation
import com.example.presentation.home_screen.common_item.CategoriesRow
import com.example.presentation.home_screen.common_item.CategoriesVerticalGrid
import com.example.presentation.home_screen.common_item.FilterProduct
import com.example.presentation.home_screen.common_item.ProductVerticalGrid
import com.example.presentation.home_screen.common_item.SearchBar
import com.example.presentation.home_screen.common_item.TopBar
import com.example.utils.ApiResult
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToSearch: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    val categories by homeViewModel.categories.collectAsStateWithLifecycle()
    val products by homeViewModel.products.collectAsStateWithLifecycle()
    var isShowAll by remember { mutableStateOf(false) }
    var isFilter by remember { mutableStateOf(false) }

    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            delay(1500)
            homeViewModel.refreshScreen()
            state.endRefresh()
        }
    }
    val scaleFraction = if (state.isRefreshing) 1f else
        LinearOutSlowInEasing.transform(state.progress).coerceIn(0f, 1f)

    Scaffold(
        modifier = Modifier.nestedScroll(state.nestedScrollConnection),
    ) {
        Box(Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!state.isRefreshing) {
                    TopBar(homeViewModel)
                    {
                        navigateToCart()
                    }
                    SearchBar(
                        homeViewModel = homeViewModel,
                        isSearchScreen = false,
                        padding = 0.dp,
                        navigateToSearch = { navigateToSearch() },
                        navigateToCart = {},
                        navigateBack = {}
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (categories) {
                            is ApiResult.Error -> {
                                Toast.makeText(LocalContext.current, categories.error, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is ApiResult.Loading -> {
                                LazyRow(
                                    modifier = Modifier.padding(vertical = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    items(5) {
                                        ShimmerAnimation(true)
                                    }
                                }
                            }

                            is ApiResult.Success -> {
                                val list = categories.data ?: emptyList()
                                if (!isShowAll) {
                                    if (list.isEmpty()) NoResultBox("No fetched categories")
                                    CategoriesRow(
                                        homeViewModel = homeViewModel,
                                        list = list,
                                        categoryClicked = { isFilter = false },
                                        showAllClicked = { isShowAll = true }
                                    )
                                } else {
                                    CategoriesVerticalGrid(
                                        homeViewModel = homeViewModel,
                                        list = list,
                                        showAllClicked = {
                                            isShowAll = false
                                            isFilter = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    FilterProduct(
                        homeViewModel = homeViewModel,
                        isHomeScreen = true,
                        sortedClicked = { isFilter = true }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (products) {
                            is ApiResult.Error -> {
                                Toast.makeText(LocalContext.current, products.error, Toast.LENGTH_SHORT).show()
                            }

                            is ApiResult.Loading -> {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    items(8) {
                                        ShimmerAnimation(false)
                                    }
                                }
                            }

                            is ApiResult.Success -> {
                                val list = products.data ?: emptyList()
                                if (isFilter) {
                                    if (homeViewModel.sortedList.value.isEmpty())  NoResultBox("No search results")
                                    else ProductVerticalGrid(homeViewModel, homeViewModel.sortedList.value) { id ->
                                        navigateToDetail(id)
                                    }
                                } else {
                                    if (list.isEmpty())  NoResultBox("No results")
                                    else ProductVerticalGrid(homeViewModel, list) { id ->
                                        navigateToDetail(id)
                                    }
                                }
                            }
                        }
                    }
                }

            }

            PullToRefreshContainer(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction),
                state = state,
            )
        }
    }
}