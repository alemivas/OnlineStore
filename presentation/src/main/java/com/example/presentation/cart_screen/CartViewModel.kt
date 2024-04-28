package com.example.presentation.cart_screen

//@HiltViewModel
//class CartViewModel @Inject constructor(
//    private val getIsLoginUser: GetIsLoginUserUseCase,
//    private val saveUser: SaveUserUseCase,
//) : ViewModel() {
//
//    private val _currentCountry: MutableState<String> = mutableStateOf(Constants.countryList.first())
//    val currentCountry = _currentCountry
//
//    private val _cart: MutableState<List<Cart>> = mutableStateOf(emptyList())
//    val cart: State<List<Cart>> = _cart
//
//    init {
//        getUser()
//    }
//
//    private fun getUser() {
//        viewModelScope.launch {
//            while (true) {
//                val isLoginUser = getIsLoginUser()
//                if (isLoginUser != null) {
//                    _cart.value = isLoginUser.cartList
//                }
//                delay(1000)
//            }
//        }
//    }
//
//    fun changeCurrentCountry(country: String) {
//        _currentCountry.value = country
//    }
//
//    fun addToCart(product: Product) {
//        val currentProduct = _cart.value.find { it.product.id == product.id}
//
//        val shoppingCartLastId = _cart.value.lastOrNull()?.id ?: 0
//        val newId = shoppingCartLastId + 1
//        val newShoppingCart = Cart(newId, product, 1)
//
//        if (currentProduct == null) {
//            _cart.value += newShoppingCart
//        } else {
//            _cart.value = _cart.value.map {
//                if (it.product == product) it.copy(quantity = currentProduct.quantity + 1)
//                else it
//            }
//        }
//        viewModelScope.launch {
//            val user = getIsLoginUser()
//            if (user != null) {
//                val updatedCartList = user.copy(cartList = _cart.value)
//                saveUser(updatedCartList)
//            }
//        }
//    }
//
//    fun removeFromCart(product: Product) {
//        val currentProduct = _cart.value.find { it.product == product}
//
//        if (currentProduct != null && currentProduct.quantity > 1) {
//            _cart.value = _cart.value.map {
//                if (it.product == product) it.copy(quantity = currentProduct.quantity - 1)
//                else it
//            }
//        } else if (currentProduct != null && currentProduct.quantity >= 1) {
//            _cart.value = _cart.value.minus(Cart(currentProduct.id,product, 1))
//        }
//        viewModelScope.launch {
//            val user = getIsLoginUser()
//            if (user != null) {
//                val updatedCartList = user.copy(cartList = _cart.value)
//                saveUser(updatedCartList)
//            }
//        }
//    }
//
//    fun getSum(): Int {
//        return _cart.value.sumOf { it.product.price * it.quantity }
//    }
//
//    fun makeOrder() {
//        viewModelScope.launch {
//            val user = getIsLoginUserUseCase()
//            val currentCartList = _shoppingCart.value
//            if (user != null) {
//                val updatedShoppingCartList = user.copy(shoppingCartList = user.shoppingCartList + listOf(currentCartList))
//                saveUserUseCase(updatedShoppingCartList)
//                _shoppingCart.value = emptyList()
//            }
//        }
//    }
//}