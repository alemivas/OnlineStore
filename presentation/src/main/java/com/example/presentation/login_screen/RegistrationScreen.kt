package com.example.presentation.login_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.login_screen.common_item.ErrorMinimalDialog
import com.example.presentation.login_screen.common_item.PasswordScreenTextField
import com.example.presentation.theme.GrayDarkest
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.LoginLabelColor
import com.example.presentation.theme.Mint
import com.example.presentation.theme.PasswordBackgroundColor
import com.example.presentation.theme.PasswordLabelColor
import com.example.presentation.theme.Purple
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    loginVewModel: LoginVewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit,
) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    var isErrorName by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isErrorConfirmPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(GrayLighter, CircleShape)
                    .clickable { navigateBack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = GrayDarkest,
                )
            }

            Text(
                text = "Sign Up",
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight(700),
                fontSize = 18.sp,
                color = GrayDarkest,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "Completе your account",
            fontWeight = FontWeight(700),
            fontSize = 24.sp,
            color = GrayDarkest,
            textAlign = TextAlign.Center
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PasswordScreenTextField(
                header = "First Name",
                placeholder = "Enter your name",
                value = name.value,
                newValue = {
                    name.value = it
                    isErrorName = loginVewModel.isValidName(name.value)
                },
                isHidden = true,
                isError = isErrorName
            )

            PasswordScreenTextField(
                header = "Email",
                placeholder = "Enter your email",
                value = email.value,
                newValue = {
                    email.value = it
                    isErrorEmail = loginVewModel.isValidEmail(email.value)
                },
                isHidden = true,
                isError = isErrorEmail
            )

            PasswordScreenTextField(
                header = "Password",
                placeholder = "Enter your password",
                value = password.value,
                newValue = {
                    password.value = it
                    isErrorPassword = loginVewModel.isValidPassword(password.value)
                },
                isHidden = false,
                isError = isErrorPassword
            )

            PasswordScreenTextField(
                header = "Confirm Password",
                placeholder = "Enter your password",
                value = confirmPassword.value,
                newValue = {
                    confirmPassword.value = it
                    isErrorConfirmPassword = loginVewModel.isValidPassword(confirmPassword.value)
                    isErrorConfirmPassword = password.value != confirmPassword.value
                },
                isHidden = false,
                isError = isErrorConfirmPassword
            )
        }

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PasswordBackgroundColor)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Type of account",
                    fontSize = 16.sp,
                    color = PasswordLabelColor
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = PasswordLabelColor
                )
            }
        }

        Button(
            onClick = {
                loginVewModel.viewModelScope.launch {
                    if (!loginVewModel.checkUser(email.value, password.value)
                        && !isErrorEmail && !isErrorName && !isErrorPassword && !isErrorConfirmPassword
                        && name.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()
                    ) {
                        loginVewModel.saveUser(name.value, email.value, password.value)
                        navigateToHome()
                    } else {
                        showErrorDialog = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Mint)
        ) {
            Text(text = "Sign Up", fontSize = 16.sp)
        }

        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Already have an account? ",
                color = LoginLabelColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
            Text(
                modifier = Modifier.clickable { navigateBack() },
                text = "Login",
                color = Purple,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
        }
    }
    if (showErrorDialog) {
        ErrorMinimalDialog(
            text = "Enter data in all fields",
            onDismissRequest = { showErrorDialog = false }
        )
    }
}