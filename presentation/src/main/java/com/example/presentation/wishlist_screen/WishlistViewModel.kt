package com.example.presentation.wishlist_screen
//
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.domain.models.Category
//import com.example.domain.models.Product
//import com.example.domain.usecases.GetCategoriesUseCase
//import com.example.domain.usecases.GetProductsUseCase
//import com.example.utils.ApiResult
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//class WishlistViewModel @Inject constructor(
//    //private val getProductsUseCase: GetProductsUseCase,
//    //private val defaultDispatcher: CoroutineDispatcher
//) : ViewModel() {
//
//
//
//
//    private val _cart: MutableState<List<Product>> = mutableStateOf(emptyList())
//    val cart: State<List<Product>> = _cart
//
//    private val _searchQuery: MutableState<String> = mutableStateOf("")
//    val searchQuery = _searchQuery
//
//    private val _wishlist:
//    fun checkCart(product: Product) {
//        if (_cart.value.contains(product)) {
//            _cart.value = _cart.value.minus(product)
//        } else {
//            _cart.value = _cart.value.plus(product)
//        }
//    }
//    //fun fetchSearchList(
//    //    limit: Int,
//    //    offset: Int,
//    //    title: String? = null,
//    //    categoryId: Int? = null,
//    //    priceMin: Int? = null,
//    //    priceMax: Int? = null,
//    //) {
//    //    viewModelScope.launch {
//    //        getProductsUseCase(limit, offset, title, categoryId, priceMin, priceMax)
//    //            .flowOn(defaultDispatcher)
//    //            .catch {
//    //                _searchList.value = ApiResult.Error(it.message ?: "Something went wrong")
//    //            }
//    //            .collect{
//    //                _searchList.value = it
//    //            }
//    //    }
//    //}
//
////    fun checkSearchList(searchQuery: String) {
////        if (_searchHistoryList.value.contains(searchQuery)) {
////            _searchHistoryList.value = _searchHistoryList.value.minus(searchQuery)
////        } else {
////            _searchHistoryList.value = _searchHistoryList.value.plus(searchQuery)
////        }
////    }
//
//
//}
//
