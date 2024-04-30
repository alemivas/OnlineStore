package com.example.presentation.login_screen.common_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.Gray
import com.example.presentation.theme.LoginBackgroundColor
import com.example.presentation.theme.LoginBorderColor
import com.example.presentation.theme.LoginLabelColor
import com.example.presentation.theme.Mint


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
