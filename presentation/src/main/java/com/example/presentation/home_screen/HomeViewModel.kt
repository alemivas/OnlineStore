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
import com.example.utils.Constants
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

    private val _currentCountry: MutableState<String> = mutableStateOf(Constants.Country.USA.name)
    val currentCountry = _currentCountry

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

    private val _checkedProducts: MutableState<List<Cart>> = mutableStateOf(emptyList())

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
                }
                delay(1000)
            }
        }
    }

    private fun saveUserCart(carts: List<Cart>) {
        viewModelScope.launch {
            val user = getIsLoginUser()
            if (user != null) {
                val updatedCartList = user.copy(cartList = carts)
                saveUser(updatedCartList)
            }
        }
    }

    fun refreshScreen() {
        fetchCategories()
        fetchProducts(30, 0)
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
            if (_searchHistoryList.value.size >= 10) return
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

    fun checkCart(product: Product) {
        val currentCart = _cart.value.find { it.product == product}

        val shoppingCartLastId = _cart.value.lastOrNull()?.id ?: 0
        val newId = shoppingCartLastId + 1
        val newShoppingCart = Cart(newId, product, 1)

        if (currentCart == null) _cart.value += newShoppingCart
        else {
            _cart.value = _cart.value.minus(currentCart)
            val checkedCart = _checkedProducts.value.find { it.product == product}
            if (checkedCart != null) _checkedProducts.value = _checkedProducts.value.minus(checkedCart)
        }

        saveUserCart(_cart.value)
    }

    fun addToCart(cart: Cart) {
        val currentProduct = _cart.value.find { it.product == cart.product}

        if (currentProduct != null) {
            _cart.value = _cart.value.map {
                if (it.product == cart.product) it.copy(quantity = currentProduct.quantity + 1)
                else it
            }
            _checkedProducts.value = _checkedProducts.value.map {
                if (it.product == cart.product) it.copy(quantity = currentProduct.quantity + 1)
                else it
            }
        }
        saveUserCart(_cart.value)
    }

    fun removeFromCart(cart: Cart) {
        val currentProduct = _cart.value.find { it.product == cart.product}

        if (currentProduct != null && currentProduct.quantity > 1) {
            _cart.value = _cart.value.map {
                if (it.product == cart.product) it.copy(quantity = currentProduct.quantity - 1)
                else it
            }
            _checkedProducts.value = _checkedProducts.value.map {
                if (it.product == cart.product) it.copy(quantity = currentProduct.quantity - 1)
                else it
            }
        } else if (currentProduct != null && currentProduct.quantity == 1) {
            deleteFromCart(cart)
        }
        saveUserCart(_cart.value)
    }

    fun deleteFromCart(cart: Cart) {
        _cart.value = _cart.value.minus(cart)

        val checkedCart = _checkedProducts.value.find { it == cart}
        if (checkedCart != null) _checkedProducts.value = _checkedProducts.value.minus(checkedCart)
        saveUserCart(_cart.value)
    }

    fun sortedProductList(
        filter: Constants.SortType,
        products: List<Product>,
        priceMin: Int?,
        priceMax: Int?
    ) {
        _sortedList.value = when (filter) {
            Constants.SortType.NAME -> products.sortedBy { it.title }
            Constants.SortType.REVERSE_NAME -> products.sortedByDescending { it.title }
            Constants.SortType.PRICE -> products.sortedBy { it.price }
            Constants.SortType.REVERSE_PRICE -> products.sortedByDescending { it.price }
            Constants.SortType.RANGE -> {
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


    fun changeCurrentCountry(country: String) {
        _currentCountry.value = country
        viewModelScope.launch {
            val user = getIsLoginUser()
            if (user != null) {
                val updatedUser = user.copy(country = country)
                saveUser(updatedUser)
            }
        }
    }

    fun getConvertedPrice(price: Int): String {
        return when (_currentCountry.value) {
            Constants.Country.USA.toString() -> "$ $price"
            Constants.Country.BRAZIL.toString() -> "R$ ${price * 5}"
            Constants.Country.ARGENTINA.toString() -> "$ ${price * 877}"
            Constants.Country.MEXICO.toString() -> "$ ${price * 17}"
            Constants.Country.EUROPE.toString() -> "€ ${"%.2f".format(price * 0.9)}"
            Constants.Country.UNITED_KINGDOM.toString() -> "£ ${"%.2f".format(price * 0.8)}"
            Constants.Country.JAPAN.toString() -> "¥ ${price * 156}"
            Constants.Country.RUSSIA.toString() -> "₽ ${price * 90}"
            Constants.Country.CHINA.toString() -> "¥ ${price * 7}"
            else -> { "$ $price" }
        }
    }

    fun getBucksPrice(price: Int): Int {
        return when (_currentCountry.value) {
            Constants.Country.USA.toString() -> price
            Constants.Country.BRAZIL.toString() -> price / 5
            Constants.Country.ARGENTINA.toString() -> price / 877
            Constants.Country.MEXICO.toString() -> price / 17
            Constants.Country.EUROPE.toString() -> (price / 0.9).toInt()
            Constants.Country.UNITED_KINGDOM.toString() -> (price / 0.8).toInt()
            Constants.Country.JAPAN.toString() -> price / 156
            Constants.Country.RUSSIA.toString() -> price / 90
            Constants.Country.CHINA.toString() -> price / 7
            else -> { price }
        }
    }

    fun changeCheckedProducts(cart: Cart) {
        if (_checkedProducts.value.contains(cart)) {
            _checkedProducts.value = _checkedProducts.value.minus(cart)
        } else {
            _checkedProducts.value = _checkedProducts.value.plus(cart)
        }
    }

    fun getSum(): String {
        val sum = _checkedProducts.value.sumOf { it.product.price * it.quantity }
        return getConvertedPrice(sum)
    }

    fun isCartNotEmpty(): Boolean{
        return _checkedProducts.value.isNotEmpty()
    }
    fun makeOrder() {
        if (_checkedProducts.value.isEmpty() || _cart.value.isEmpty()) {

        } else if(_checkedProducts.value.isNotEmpty()) {
            val newUserCart = _cart.value.minus(_checkedProducts.value)
            saveUserCart(newUserCart)
        }
        _checkedProducts.value = emptyList()
    }

    fun checkedStates(cart: Cart): Boolean {
        return _checkedProducts.value.contains(cart)
    }
}