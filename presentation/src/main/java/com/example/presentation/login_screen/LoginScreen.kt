package com.example.presentation.login_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.presentation.R
import com.example.presentation.theme.Gray
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.LoginBackgroundColor
import com.example.presentation.theme.LoginBorderColor
import com.example.presentation.theme.LoginLabelColor
import com.example.presentation.theme.Mint
import com.example.presentation.theme.Red

@Preview
@Composable
fun LoginScreen() {
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
            label = "Login",
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
                if (login.value.isEmpty() || password.value.isEmpty()) showErrorDialog = true
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
                modifier = Modifier.clickable { /*TODO*/ },
                text = "Register here",
                color = Mint,
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
            )
        }
    }
    if (showErrorDialog) {
        MinimalDialog(
            text = "Enter login and password",
            onDismissRequest = { showErrorDialog = false }
        )
    }

}

@Composable
fun LoginScreenTextField(
    header: String,
    label: String,
    value: MutableState<String>,
    isHidden: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = header,
            color = LoginLabelColor
        )
        OutlinedTextField(
            value = value.value,
            onValueChange = { value.value = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (!isHidden.value) R.drawable.eye else R.drawable.hide
                    ),
                    contentDescription = null,
                    modifier = Modifier.clickable { isHidden.value = !isHidden.value },
                    tint = Gray
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Mint,
                focusedBorderColor = Mint,
                focusedLabelColor = Mint,
                focusedContainerColor = LoginBackgroundColor,
                unfocusedBorderColor = LoginBorderColor,
                unfocusedTextColor = LoginLabelColor,
                unfocusedLabelColor = LoginLabelColor,
                unfocusedContainerColor = LoginBackgroundColor
            ),
            visualTransformation = if (isHidden.value) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}

@Composable
fun MinimalDialog(
    text: String,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            colors = CardColors(
                containerColor = GrayLighter,
                contentColor = Red,
                disabledContainerColor = GrayLighter,
                disabledContentColor = GrayLighter
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text)
                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Mint)
                ) {
                    Text("OK")
                }
            }
        }
    }
}
