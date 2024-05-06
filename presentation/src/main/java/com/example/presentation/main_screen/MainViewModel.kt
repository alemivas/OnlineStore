package com.example.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.domain.usecases.user_db_use_case.GetAllUserUseCase
import com.example.domain.usecases.user_db_use_case.GetIsLoginUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import com.example.presentation.R
import com.example.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUsers: GetAllUserUseCase,
    private val saveUser: SaveUserUseCase,
    private val getIsLoginUserUseCase: GetIsLoginUserUseCase,
) : ViewModel() {

    private val _isManager = MutableStateFlow(false)
    val isManager: StateFlow<Boolean> = _isManager

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _currentProfileImage = MutableStateFlow(_currentUser.value?.image?.toIntOrNull()
        ?: R.drawable.ic_profile)
    val currentProfileImage: StateFlow<Int> = _currentProfileImage

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            getAllUsers()?.forEach {
                saveUser(it.copy(isLogin = false))
            }
        }
    }

    fun updateUserStatus(isManager: Boolean) {
        viewModelScope.launch {
            _isManager.value = isManager
            val updatedUser = getIsLoginUserUseCase()?.copy(isManager = isManager)
            if (updatedUser != null) {
                saveUser(updatedUser)
            }
        }
    }

    suspend fun checkUser(email: String, password: String): Boolean {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                val user = getAllUsers()
                val isUserExists = user?.find { it.email == email && it.password == password } != null
                continuation.resume(isUserExists)
            }
        }
    }

    fun saveIsLoginStatus(email: String) {
        viewModelScope.launch {
            val userList = getAllUsers()
            val user = userList?.find { it.email == email }
            if (user != null) {
                _isManager.value = user.isManager
                saveUser(user.copy(isLogin = true))

                val otherUsers = userList - user
                otherUsers.forEach {
                    saveUser(it.copy(isLogin = false))
                }
            } else {
                userList?.forEach {
                    saveUser(it.copy(isLogin = false))
                }
            }
        }
    }

    fun saveUser(name: String, email: String, password: String, typeAccount: Constants.TypeOfAccount) {
        viewModelScope.launch {
            val userList = getAllUsers() ?: emptyList()
            val user = userList.find { it.email == email }
            if (user == null) {
                val newUser = User(
                    id = if (userList.isEmpty()) 1 else userList.last().id + 1,
                    name = name,
                    email = email,
                    password = password,
                    image = "",
                    favoriteProductList = emptyList(),
                    cartList = emptyList(),
                    country = Constants.Country.entries.map { it.toString() }.first(),
                    isManager = typeAccount != Constants.TypeOfAccount.USER,
                    isLogin = true
                )
                saveUser(newUser)
                _currentUser.value = newUser
                _isManager.value = newUser.isManager
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            val updatedUser = _currentUser.value?.copy(isLogin = false)
            if (updatedUser != null) {
                saveUser(updatedUser)
            }
        }
    }

    fun updateImageAccount(image:Int) {
        viewModelScope.launch {
            val user = getIsLoginUserUseCase()
            if (user != null) {
                val updatedUser = user.copy(image = image.toString())
                _currentProfileImage.value = image
                saveUser(updatedUser)
            }
        }
    }

    fun isValidName(text: String): Boolean {
        return text.isEmpty()
    }

    fun isValidPassword(text: String): Boolean {
        return text.isEmpty()
    }

    fun isValidEmail(text: String): Boolean {
        return text.isEmpty() || !text.contains("@").and(text.endsWith(".ru") || text.endsWith(".com"))
    }
}