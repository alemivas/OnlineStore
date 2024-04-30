package com.example.presentation.home_screen.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.models.Category
import com.example.presentation.home_screen.HomeViewModel

@Composable
fun CategoriesVerticalGrid(
    homeViewModel: HomeViewModel,
    list: List<Category>,
    showAllClicked: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(list.size) { category ->
            CategoryItem(category = list[category]) {
                showAllClicked()
                homeViewModel.fetchProducts(
                    limit = 10,
                    offset = 0,
                    categoryId = list[category].id
                )
            }
        }
    }
}