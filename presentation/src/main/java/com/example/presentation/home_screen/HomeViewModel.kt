package com.example.presentation.home_screen

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    val countryList = listOf(
        "USA", "Canada", "Brazil", "Argentina", "Australia",
        "Europe", "United Kingdom", "Japan", "Russia", "China"
    )
    private val _favoriteList: MutableState<List<Product>> = mutableStateOf(emptyList())
    val favoriteList: State<List<Product>> = _favoriteList

//    val country = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]?.country

    private val _categories = MutableStateFlow<ApiResult<List<Category>>>(ApiResult.Loading())
    val categories = _categories.asStateFlow()

    private val _products = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading())
    val products = _products.asStateFlow()

    private val _searchList = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading())
    val searchList = _searchList.asStateFlow()

    private val _sortedList: MutableState<List<Product>> = mutableStateOf(emptyList())
    val sortedList: State<List<Product>> = _sortedList

    private val _cart: MutableState<List<Product>> = mutableStateOf(emptyList())
    val cart: State<List<Product>> = _cart

    private val _searchHistoryList: MutableState<List<String>> = mutableStateOf(emptyList())
    val searchHistoryList: State<List<String>> = _searchHistoryList

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery = _searchQuery

    init {
        fetchCategories()
        fetchProducts(30, 0)
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .flowOn(defaultDispatcher)
                .catch {
                    _categories.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect {
                    _categories.value = it
                }
        }
    }

    fun favSave(
        product: Product,
    ) {
        if (_favoriteList.value.contains(product)) {
            _favoriteList.value -= product
        } else {
            _favoriteList.value += product
        }
    }

    fun fetchProducts(
        limit: Int,
        offset: Int,
        title: String? = null,
        categoryId: Int? = null,
        priceMin: Int? = null,
        priceMax: Int? = null,
    ) {
        viewModelScope.launch {
            getProductsUseCase(limit, offset, title, categoryId, priceMin, priceMax)
                .flowOn(defaultDispatcher)
                .catch {
                    _products.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect {
                    _products.value = it
                }
        }
    }

    fun fetchSearchList(
        limit: Int,
        offset: Int,
        title: String? = null,
        categoryId: Int? = null,
        priceMin: Int? = null,
        priceMax: Int? = null,
    ) {
        viewModelScope.launch {
            getProductsUseCase(limit, offset, title, categoryId, priceMin, priceMax)
                .flowOn(defaultDispatcher)
                .catch {
                    _searchList.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect {
                    _searchList.value = it
                }
        }
    }

    fun checkSearchList(searchQuery: String) {
        if (_searchHistoryList.value.contains(searchQuery)) {
            _searchHistoryList.value = _searchHistoryList.value.minus(searchQuery)
        } else {
            _searchHistoryList.value = _searchHistoryList.value.plus(searchQuery)
        }
    }

    fun isVisibleHistorySearchList(): Boolean {
        return _searchQuery.value.isEmpty()
    }

    fun changeSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun clearSearchList() {
        _searchHistoryList.value = emptyList()
    }
    fun isFavorite(product: Product):Boolean{
        return _favoriteList.value.contains(product)
    }

    fun checkCart(product: Product) {
        if (_cart.value.contains(product)) {
            _cart.value = _cart.value.minus(product)
        } else {
            _cart.value = _cart.value.plus(product)
        }
    }

    fun sortedProductList(
        filter: SortType,
        products: List<Product>,
        priceMin: Int?,
        priceMax: Int?
    ) {
        _sortedList.value = when (filter) {
            SortType.NAME -> products.sortedBy { it.title }
            SortType.PRICE -> products.sortedBy { it.price }
            SortType.RANGE -> {
                val filteredProducts = products.filter { product ->
                    val price = product.price
                    if (priceMin == null || priceMax == null) return
                    price in priceMin..priceMax
                }
                filteredProducts.sortedBy { it.price }
            }
        }
    }
}

enum class SortType {
    NAME,
    PRICE,
    RANGE
}