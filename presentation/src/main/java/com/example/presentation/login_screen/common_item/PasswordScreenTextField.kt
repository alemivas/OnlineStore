package com.example.presentation.login_screen.common_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.Gray
import com.example.presentation.theme.LoginLabelColor
import com.example.presentation.theme.Mint
import com.example.presentation.theme.PasswordBackgroundColor
import com.example.presentation.theme.PasswordLabelColor

@Composable
fun PasswordScreenTextField(
    header: String,
    placeholder: String,
    value: String,
    newValue: (String) -> Unit,
    isHidden: Boolean,
    isError: Boolean,
) {
    val isHiddenPassword = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = header,
            color = LoginLabelColor
        )
        OutlinedTextField(
            value = value,
            onValueChange = { newValue(it)},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            placeholder = { Text(text = placeholder, color = PasswordLabelColor) },
            trailingIcon = {
                if (!isHidden) {
                    Icon(
                        painter = painterResource(
                            id = if (!isHiddenPassword.value) R.drawable.eye else R.drawable.hide
                        ),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            isHiddenPassword.value = !isHiddenPassword.value
                        },
                        tint = Gray
                    )
                }
            },
            isError = isError,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Mint,
                focusedBorderColor = Color.Transparent,
                focusedContainerColor = PasswordBackgroundColor,
                unfocusedBorderColor = Color.Transparent,
                unfocusedTextColor = PasswordLabelColor,
                unfocusedContainerColor = PasswordBackgroundColor
            ),
            visualTransformation =
            if (!isHidden) {
                if (isHiddenPassword.value) PasswordVisualTransformation()
                else VisualTransformation.None
            } else VisualTransformation.None
        )
    }
}