package com.example.presentation.home_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Cart
import com.example.domain.models.Category
import com.example.domain.models.Product
import com.example.domain.usecases.api_use_cases.GetCategoriesUseCase
import com.example.domain.usecases.api_use_cases.GetProductsUseCase
import com.example.domain.usecases.product_db_use_cases.GetAllProductsFromCacheUseCase
import com.example.domain.usecases.product_db_use_cases.InsertProductListIntoCache
import com.example.domain.usecases.user_db_use_case.GetIsLoginUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import com.example.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getCategories: GetCategoriesUseCase,
    private val getProducts: GetProductsUseCase,
    private val insertProductListIntoCache: InsertProductListIntoCache,
    private val getAllProductsFromCache: GetAllProductsFromCacheUseCase,
    private val getIsLoginUser: GetIsLoginUserUseCase,
    private val saveUser: SaveUserUseCase,
    ) : ViewModel() {

    val countryList = listOf(
        "USA", "Canada", "Brazil", "Argentina", "Australia",
        "Europe", "United Kingdom", "Japan", "Russia", "China"
    )

//    val country = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]?.country

    private val _categories = MutableStateFlow<ApiResult<List<Category>>>(ApiResult.Loading())
    val categories = _categories.asStateFlow()

    private val _products = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading())
    val products = _products.asStateFlow()

    private val _searchList = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading())
    val searchList = _searchList.asStateFlow()

    private val _sortedList: MutableState<List<Product>> = mutableStateOf(emptyList())
    val sortedList: State<List<Product>> = _sortedList

    private val _cart: MutableState<List<Cart>> = mutableStateOf(emptyList())
    val cart: State<List<Cart>> = _cart

    private val _searchHistoryList: MutableState<List<String>> = mutableStateOf(emptyList())
    val searchHistoryList: State<List<String>> = _searchHistoryList

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _selectedProduct = mutableStateOf<Product?>(null)
    val selectedProduct: State<Product?> = _selectedProduct

    private val _favoriteList: MutableState<List<Product>> =
        mutableStateOf(emptyList())
    val favoriteList: State<List<Product>> = _favoriteList

    init {
        fetchCategories()
        fetchProducts(30, 0)
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            while (true) {
                val isLoginUser = getIsLoginUser()
                if (isLoginUser != null) {
                    _cart.value = isLoginUser.cartList
                    _favoriteList.value = isLoginUser.favoriteProductList
                }
                delay(1000)
            }
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategories()
                .flowOn(dispatcher)
                .catch {
                    _categories.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect{
                    _categories.value = it

                }
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
            getProducts(limit, offset, title, categoryId, priceMin, priceMax)
                .flowOn(dispatcher)
                .catch {
                    _products.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect{
                    _products.value = it
                    insertProductListIntoCache(it.data ?: emptyList())
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
                getProducts(limit, offset, title, categoryId, priceMin, priceMax)
                    .flowOn(dispatcher)
                    .catch {
                        _searchList.value = ApiResult.Error(it.message ?: "Something went wrong")
                    }
                    .collect{
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

    fun isContainsCart(product: Product): Boolean {
        return _cart.value.find { it.product == product } != null
    }

    fun addToCart(product: Product) {
        val currentProduct = _cart.value.find { it.product.id == product.id}

        val shoppingCartLastId = _cart.value.lastOrNull()?.id ?: 0
        val newId = shoppingCartLastId + 1
        val newShoppingCart = Cart(newId, product, 1)

        if (currentProduct == null) {
            _cart.value += newShoppingCart
        } else {
            _cart.value = _cart.value.map {
                if (it.product == product) it.copy(quantity = currentProduct.quantity + 1)
                else it
            }
        }
        viewModelScope.launch {
            val user = getIsLoginUser()
            if (user != null) {
                val updatedCartList = user.copy(cartList = _cart.value)
                saveUser(updatedCartList)
            }
        }
    }

    fun removeFromCart(product: Product) {
        val currentProduct = _cart.value.find { it.product == product}

        if (currentProduct != null && currentProduct.quantity > 1) {
            _cart.value = _cart.value.map {
                if (it.product == product) it.copy(quantity = currentProduct.quantity - 1)
                else it
            }
        } else if (currentProduct != null && currentProduct.quantity >= 1) {
            _cart.value = _cart.value.minus(Cart(currentProduct.id,product, 1))
        }
        viewModelScope.launch {
            val user = getIsLoginUser()
            if (user != null) {
                val updatedCartList = user.copy(cartList = _cart.value)
                saveUser(updatedCartList)
            }
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

    fun getProduct(id: Int) {
        viewModelScope.launch {
            val products = getAllProductsFromCache()
            if (products != null) {
                _selectedProduct.value = products.find { it.id == id }
            }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val user = getIsLoginUser()
            if (_favoriteList.value.contains(product) && user != null) {
                val updatedFavoriteList = user.copy(
                    favoriteProductList = user.favoriteProductList - listOf(product).toSet()
                )
                saveUser(updatedFavoriteList)
                _favoriteList.value = updatedFavoriteList.favoriteProductList
            } else if (!_favoriteList.value.contains(product) && user != null) {
                val updatedFavoriteList = user.copy(
                    favoriteProductList = user.favoriteProductList + listOf(product).toSet()
                )
                saveUser(updatedFavoriteList)
                _favoriteList.value = updatedFavoriteList.favoriteProductList
            }
        }
    }

    fun isFavoriteChecked(product: Product): Boolean {
        return _favoriteList.value.contains(product)
    }
}

enum class SortType {
    NAME,
    PRICE,
    RANGE
}