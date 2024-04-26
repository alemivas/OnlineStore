package com.example.presentation.login_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVewModel @Inject constructor(

) : ViewModel() {

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