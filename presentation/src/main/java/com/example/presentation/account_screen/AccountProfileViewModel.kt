package com.example.presentation.account_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Category
import com.example.domain.models.User
import com.example.domain.usecases.user_db_use_case.GetAllUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import com.example.presentation.R
import com.example.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.StateFlow


@HiltViewModel
class AccountProfileViewModel @Inject constructor(
    private val getAllUsers: GetAllUserUseCase,
    private val saveUser: SaveUserUseCase
) : ViewModel() {



    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState
    val currentProfileImage: MutableState<Int?> = mutableStateOf(userState.value?.image?.toIntOrNull() ?: R.drawable.ic_profile)
    val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loading = _loading

    init {
        getUser()
        observeUserState()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            getAllUsers()?.find { it.isLogin }?.let {
                _userState.value = it
                _loading.value = false
            }
        }
    }
    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.value?.let { currentUser ->
                val updatedUser = currentUser.copy(isLogin = false)
                saveUser(updatedUser)
            }
        }
    }
    private fun observeUserState() {
        viewModelScope.launch {
            userState.collect { user ->
                user?.let {
                    currentProfileImage.value = it.image.toIntOrNull() ?: R.drawable.ic_profile
                }
            }
        }
    }

    fun updateTypeAccount(isManager:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.value?.let { currentUser ->
                val updatedUser = currentUser.copy(isManager = isManager)
                saveUser(updatedUser)
            }
        }
    }

    fun updateImageAccount(image:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.value?.let { currentUser ->
                val updatedUser = currentUser.copy(image = image.toString())
                saveUser(updatedUser)
                currentProfileImage.value = image
            }
        }
    }
}

