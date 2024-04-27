package com.example.presentation.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.domain.models.Product
import com.example.presentation.home_screen.HomeViewModel

@Composable
fun ProductVerticalGrid(
    homeViewModel: HomeViewModel,
    listProduct: List<Product>,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listProduct.size) { product ->
            ProductItem(
                homeViewModel = homeViewModel,
                product = listProduct[product],
                navigateToDetail = { navigateToDetail(it) }
            )
        }
    }
}