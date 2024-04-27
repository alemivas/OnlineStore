package com.example.presentation.wishlist_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Cart
import com.example.domain.models.Product
import com.example.domain.usecases.user_db_use_case.GetIsLoginUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getIsLoginUser: GetIsLoginUserUseCase,
    private val saveUser: SaveUserUseCase,
) : ViewModel() {

    private val _cart: MutableState<List<Cart>> = mutableStateOf(emptyList())
    val cart: State<List<Cart>> = _cart

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _favoriteList: MutableState<List<Product>> =
        mutableStateOf(emptyList())
    val favoriteList: State<List<Product>> = _favoriteList


    init {
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

    fun getSearchFavoriteList() : List<Product> {
        return _favoriteList.value.filter { it.title.contains(_searchQuery.value) }
    }


    fun changeSearchQuery(query: String) {
        _searchQuery.value = query
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
}