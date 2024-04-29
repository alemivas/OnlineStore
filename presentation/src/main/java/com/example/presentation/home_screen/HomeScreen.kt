package com.example.presentation.home_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.home_screen.common_item.CategoriesRow
import com.example.presentation.home_screen.common_item.CategoriesVerticalGrid
import com.example.presentation.home_screen.common_item.FilterProduct
import com.example.presentation.home_screen.common_item.ProductVerticalGrid
import com.example.presentation.home_screen.common_item.SearchBar
import com.example.presentation.home_screen.common_item.TopBar
import com.example.presentation.theme.GrayLight
import com.example.utils.ApiResult

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToSearch: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    val categories by homeViewModel.categories.collectAsState()
    val products by homeViewModel.products.collectAsState()
    var isShowAll by remember { mutableStateOf(false) }
    var isFilter by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TopBar(homeViewModel) { navigateToCart() }
        SearchBar(
            homeViewModel = homeViewModel,
            isSearchScreen = false,
            padding = 0.dp,
            navigateToSearch = { navigateToSearch() },
            navigateToCart = navigateToCart,
            navigateBack = {})
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
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = GrayLight
                        )
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
                    CircularProgressIndicator(modifier = Modifier.size(40.dp), color = GrayLight)
                }

                is ApiResult.Success -> {
                    val list = products.data ?: emptyList()
                    if (isFilter) {
                        if (homeViewModel.sortedList.value.isEmpty())  NoResultBox("No search results")
                        else ProductVerticalGrid(homeViewModel, homeViewModel.sortedList.value) {
                            navigateToDetail(it)
                        }
                    } else {
                        if (list.isEmpty())  NoResultBox("No results")
                        else ProductVerticalGrid(homeViewModel, list) {
                            navigateToDetail(it)
                        }
                    }
                }
            }
        }
    }
}