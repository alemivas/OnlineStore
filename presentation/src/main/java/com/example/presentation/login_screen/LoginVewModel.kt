package com.example.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.domain.usecases.user_db_use_case.GetAllUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import com.example.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class LoginVewModel @Inject constructor(
    private val getAllUsers: GetAllUserUseCase,
    private val saveUser: SaveUserUseCase,
) : ViewModel() {

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
                saveUser(user.copy(isLogin = true))

                val otherUsers = userList - user
                otherUsers.forEach {
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
                saveUser(
                    User(
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
                )
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

