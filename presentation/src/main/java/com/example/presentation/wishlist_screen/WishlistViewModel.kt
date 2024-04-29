package com.example.presentation.wishlist_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Category
import com.example.domain.models.Product
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.domain.usecases.GetProductsUseCase
import com.example.utils.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class WishlistViewModel @Inject constructor(
    //private val getProductsUseCase: GetProductsUseCase,
    //private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _products = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading())
    val products = _products.asStateFlow()

    private val _cart: MutableState<List<Product>> = mutableStateOf(emptyList())
    val cart: State<List<Product>> = _cart

    private val _sortedList: MutableState<List<Product>> = mutableStateOf(emptyList())
    val sortedList: State<List<Product>> = _sortedList

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchHistoryList: MutableState<List<String>> = mutableStateOf(emptyList())
    val searchHistoryList: State<List<String>> = _searchHistoryList

    private val _searchList = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading())
    val searchList = _searchList.asStateFlow()

    fun checkCart(product: Product) {
        if (_cart.value.contains(product)) {
            _cart.value = _cart.value.minus(product)
        } else {
            _cart.value = _cart.value.plus(product)
        }
    }

    fun checkSearchList(searchQuery: String) {
        if (_searchHistoryList.value.contains(searchQuery)) {
            _searchHistoryList.value = _searchHistoryList.value.minus(searchQuery)
        } else {
            _searchHistoryList.value = _searchHistoryList.value.plus(searchQuery)
        }
    }


}

