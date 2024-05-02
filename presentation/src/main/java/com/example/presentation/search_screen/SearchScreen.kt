package com.example.presentation.search_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.common_item.NoResultBox
import com.example.presentation.home_screen.HomeViewModel
import com.example.presentation.home_screen.common_item.FilterProduct
import com.example.presentation.home_screen.common_item.ProductVerticalGrid
import com.example.presentation.home_screen.common_item.SearchBar
import com.example.presentation.search_screen.common_item.LastSearchItem
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.GrayLight
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.Red
import com.example.utils.ApiResult

@Composable
fun SearchScreen(
    homeViewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit,
    navigateToCart: () -> Unit,
    navigateBack: () -> Unit,
) {
    val products by homeViewModel.searchList.collectAsState()
    var isFilter by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchBar(
            homeViewModel = homeViewModel,
            isSearchScreen = true,
            padding = 16.dp,
            navigateToSearch = {},
            navigateToCart = navigateToCart,
            navigateBack = navigateBack
        )
        HorizontalDivider(color = GrayLighter)

        if (homeViewModel.isVisibleHistorySearchList()) {
            HistorySearch(homeViewModel = homeViewModel)
        } else {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center) {
                when (products) {
                    is ApiResult.Error -> {
                        Toast.makeText(LocalContext.current,products.error , Toast.LENGTH_SHORT).show()
                    }
                    is ApiResult.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.size(40.dp), color = GrayLight)
                    }
                    is ApiResult.Success -> {
                        val list = products.data ?: emptyList()
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            FilterProduct(
                                homeViewModel = homeViewModel,
                                isHomeScreen = false,
                                sortedClicked = { isFilter = true}
                            )
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
    }
}

@Composable
fun HistorySearch(
    homeViewModel: HomeViewModel,
) {
    val searchList = homeViewModel.searchHistoryList.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Last search",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = GrayDark,
            )
            Text(
                text = "Clear all",
                modifier = Modifier.clickable { homeViewModel.clearSearchList() },
                fontWeight = FontWeight(500),
                fontSize = 12.sp,
                lineHeight = 15.sp,
                color = Red,
            )
        }
        if (homeViewModel.searchHistoryList.value.isEmpty()) {
            NoResultBox("No search history")
        } else {
            LazyColumn(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(searchList.size) {
                    LastSearchItem(searchList[it]) {
                        homeViewModel.checkSearchList(searchList[it])
                    }
                }
            }
        }
    }
}