package com.example.presentation.login_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.login_screen.common_item.ErrorMinimalDialog
import com.example.presentation.login_screen.common_item.LoginScreenTextField
import com.example.presentation.theme.LoginLabelColor
import com.example.presentation.theme.Mint
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginVewModel: LoginVewModel = hiltViewModel(),
    navigateToRegistration: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    val isHiddenLogin = remember { mutableStateOf(false) }
    val isHiddenPassword = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginScreenTextField(
            header = "Enter login",
            label = "Email",
            value = login,
            isHidden = isHiddenLogin
        )
        LoginScreenTextField(
            header = "Enter password",
            label = "Password",
            value = password,
            isHidden = isHiddenPassword
        )

        Button(
            onClick = {
                loginVewModel.viewModelScope.launch {
                    if (loginVewModel.checkUser(login.value, password.value)) {
                        loginVewModel.saveIsLoginStatus(login.value)
                        navigateToHome()
                    } else {
                        showErrorDialog = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Mint)
        ) {
            Text(text = "Enter", fontSize = 16.sp)
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Don't you have an account?",
                color = LoginLabelColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
            )
            Text(
                modifier = Modifier.clickable {
                    navigateToRegistration()
                },
                text = "Register here",
                color = Mint,
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
            )
        }
    }
    if (showErrorDialog) {
        ErrorMinimalDialog(
            text = "Enter login and password",
            onDismissRequest = { showErrorDialog = false }
        )
    }
}
