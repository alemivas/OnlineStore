package com.example.presentation.account_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.User
import com.example.presentation.R
import com.example.presentation.theme.DarkBlue
import com.example.presentation.theme.GrayWithBlue

@Composable
fun Profile(
    imageProfile: Int,
    user: User,
    editIcon: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 25.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = imageProfile),
                contentDescription = "image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "edit icon",
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable { editIcon() }
            )
        }
        ProfileInfo(user)

    }
}

@Composable
fun ProfileInfo(
    user: User
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column {
        Text(
            text = user.name,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
            color = DarkBlue,
            modifier = Modifier.widthIn(max = 105.dp),
            softWrap = false
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = user.email,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
            color = GrayWithBlue,
            modifier = Modifier.widthIn(max = 105.dp),
            softWrap = false
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.eye),
                contentDescription = "",
                Modifier
                    .size(16.dp)
                    .clickable { isPasswordVisible = !isPasswordVisible }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = if (isPasswordVisible) user.password else "*".repeat(user.password.length),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp
                ),
                color = GrayWithBlue,
                modifier = Modifier.widthIn(max = 105.dp),
                softWrap = false
            )
        }
    }
}