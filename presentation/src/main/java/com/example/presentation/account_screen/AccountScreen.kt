package com.example.presentation.account_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.presentation.R
import com.example.presentation.theme.DarkBlue
import com.example.presentation.theme.GrayWhite
import com.example.presentation.theme.GrayWithBlue
import com.example.presentation.theme.LightDarkGray
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Red

@Composable
fun AccountScreen() {
    var showDialog by remember { mutableStateOf(false) }


    ImageProfile() {showDialog = true}
    ProfileInfo()
    TypeOfAccount(){/* TODO typeAcc */}
    TermsConditions {/* TODO termsCond */}
    SignOut {/* TODO signOut */}
    if (showDialog) {
        EditProfileDialog(onDismiss = { showDialog = false }, toTakePhoto = {}, toFindPhotoDir = {}, toDeletePhoto = {})
    }
}

@Composable
fun ImageProfile(iconProfile: Int = R.drawable.ic_profile, editIcon: () -> Unit) {
    Box(
        modifier = Modifier
            .width(105.dp)
            .height(100.dp)
            .padding(vertical = 113.1.dp, horizontal = 27.dp)
    ) {
        Image(
            painter = painterResource(id = iconProfile),
            contentDescription = "icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )


    }
    Box(
        modifier = Modifier
            .padding(
                vertical = 181.1.dp,
                horizontal = 100.dp
            )
            .size(32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "edit icon",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable { editIcon() }
        )
    }

}

@Composable
fun ProfileInfo(
    profileName: String = "Dev P",
    profileEmail: String = "dev@gmail.com",
    profilePassword: String = "12345678"
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(105.dp)
            .height(69.5.dp)
            .padding(
                top = 126.6.dp,
                start = 167.5.dp

            )

    ) {
        Column {
            Text(
                text = profileName,
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
                text = profileEmail,
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
                    text = if (isPasswordVisible) profilePassword else "*".repeat(profilePassword.length),
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
}

@Composable
fun TypeOfAccount(toTypeOfAccount: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                top = 509.77.dp,
                start = 29.33.dp
            )
    ) {
        Button(
            onClick = { toTypeOfAccount() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(LightGray),
            shape = RoundedCornerShape(12)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Type of account",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                    ),
                    color = LightDarkGray,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_vector),
                    contentDescription = "",
                    tint = LightDarkGray,
                    modifier = Modifier
                        .width(6.25.dp)
                        .height(10.49.dp))
            }

        }
    }
}

@Composable
fun TermsConditions(toTermsConditions: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                top = 587.18.dp,
                start = 29.33.dp
            )
    ) {
        Button(
            onClick = { toTermsConditions() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(LightGray),
            shape = RoundedCornerShape(12)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Terms & Conditions",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                    ),
                    color = LightDarkGray,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_vector),
                    contentDescription = "",
                    tint = LightDarkGray,
                    modifier = Modifier
                        .width(6.25.dp)
                        .height(10.49.dp))
            }

        }
    }
}

@Composable
fun SignOut(toSignOut: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                top = 664.59.dp,
                start = 29.33.dp
            )
    ) {
        Button(
            onClick = { toSignOut() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(LightGray),
            shape = RoundedCornerShape(12)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sign Out",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                    ),
                    color = LightDarkGray,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit),
                    contentDescription = "",
                    tint = LightDarkGray,
                    modifier = Modifier
                        .width(16.dp)
                        .height(20.dp))
            }

        }
    }
}

@Composable
fun EditProfileDialog(
    onDismiss: () -> Unit,
    toTakePhoto: () -> Unit,
    toFindPhotoDir: () -> Unit,
    toDeletePhoto: () -> Unit
    ) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .width(328.dp)
                .height(340.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change your picture",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.5.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { toTakePhoto() },
                    colors = ButtonDefaults.buttonColors(GrayWhite),
                    shape = RoundedCornerShape(12)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "",
                            modifier = Modifier
                                .size(17.72.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Take a photo",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                letterSpacing = 0.5.sp,
                                textAlign = TextAlign.Start
                            ),
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { toFindPhotoDir() },
                    colors = ButtonDefaults.buttonColors(GrayWhite),
                    shape = RoundedCornerShape(12)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dir),
                            contentDescription = "",
                            modifier = Modifier
                                .size(17.72.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Choose from your file",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                letterSpacing = 0.5.sp,
                                textAlign = TextAlign.Start
                            ),
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { toDeletePhoto() },
                    colors = ButtonDefaults.buttonColors(GrayWhite),
                    shape = RoundedCornerShape(12)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_del),
                            contentDescription = "",
                            modifier = Modifier
                                .size(17.72.dp),
                            tint = Red
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Delete photo",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                letterSpacing = 0.5.sp,
                                textAlign = TextAlign.Start
                            ),
                            color = Red
                        )
                    }
                }

            }
        }
    }
}