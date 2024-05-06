package com.example.presentation.home_screen.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.models.Category
import com.example.presentation.home_screen.HomeViewModel

@Composable
fun CategoriesRow(
    homeViewModel: HomeViewModel,
    list: List<Category>,
    categoryClicked: () -> Unit,
    showAllClicked: () -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(vertical = 16.dp)
            .height(65.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4) { category ->
            CategoryItem(category = list[category]) {
                categoryClicked()
                homeViewModel.fetchProducts(
                    limit = 10,
                    offset = 0,
                    categoryId = list[category].id
                )
            }
        }
        item { CategoryItemAll { showAllClicked() } }
    }
}