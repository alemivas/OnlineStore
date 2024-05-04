package com.example.presentation.account_screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import com.example.domain.models.User
import com.example.presentation.R
import com.example.presentation.theme.DarkBlue
import com.example.presentation.theme.GrayWhite
import com.example.presentation.theme.GrayWithBlue
import com.example.presentation.theme.LightDarkGray
import com.example.presentation.theme.LightGray
import com.example.presentation.theme.Red
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AccountScreen(
    accountProfileViewModel: AccountProfileViewModel = hiltViewModel(),
    activity: Activity
) {
    var showDialog by remember { mutableStateOf(false) }
    val userState by accountProfileViewModel.userState.collectAsStateWithLifecycle()
    var userProfilePhotoUri by remember { mutableStateOf<Uri?>(null) }
    var loading by remember { mutableStateOf(true) }

    // Получение лаунчеров для выбора изображения из галереи и съемки с камеры
    val imagePickerLauncher = rememberImagePickerLauncher { uri ->
        showDialog = false
        userProfilePhotoUri = uri
    }

    val cameraLauncher = rememberCameraLauncher { uri ->
        showDialog = false
        userProfilePhotoUri = uri
    }
    Log.d("userPh", userProfilePhotoUri.toString())

    ImageProfile(userProfilePhotoUri = userProfilePhotoUri, editIcon = { showDialog = true })
    userState?.let { ProfileInfo(it) }
    TypeOfAccount() {/* TODO typeAcc */ }
    TermsConditions {/* TODO termsCond */ }
    SignOut {/* TODO signOut */ }

    if (showDialog) {
        EditProfileDialog(
            onDismiss = { showDialog = false },
            toTakePhoto = { checkCameraPermission(activity, cameraLauncher) },
            toFindPhotoDir = { checkStoragePermission(activity, imagePickerLauncher) },
            toDeletePhoto = { deletePhoto() }
        )
    }
}

@Composable
fun ImageProfile(iconProfile: Int = R.drawable.ic_profile, editIcon: () -> Unit, userProfilePhotoUri: Uri?) {
    val userProfilePhotoUri = /* URI фотографии профиля пользователя */

        Box(
            modifier = Modifier
                .padding(vertical = 113.1.dp, horizontal = 27.dp)
                .width(105.dp)
                .height(100.dp)
        ) {
            userProfilePhotoUri?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = "User profile photo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            } ?: run {
                Image(
                    painter = painterResource(id = iconProfile),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
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
    user: User
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(
                top = 126.6.dp,
                start = 167.5.dp
            )
            .width(105.dp)
            .height(69.5.dp)
    ) {
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
                        .height(10.49.dp)
                )
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
                        .height(10.49.dp)
                )
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
                        .height(20.dp)
                )
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
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
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
                    onClick = { toTakePhoto() },
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
                    onClick = { toFindPhotoDir() },
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

fun selectImageFromGallery(launcher: ActivityResultLauncher<String>) {
    val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val uri = Uri.parse(downloadsDirectory.path)

    val intent = Intent(Intent.ACTION_PICK)
    intent.setDataAndType(uri, "image/*")

    launcher.launch(intent.toString())
}

fun takePhoto(context: Context, launcher: ActivityResultLauncher<Uri>) {
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.TITLE, "New Photo")
        put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
    }
    val photoUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    photoUri?.let { uri ->
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        launcher.launch(uri)
    }
}

fun deletePhoto() {

}


@Composable
fun rememberImagePickerLauncher(onResult: (Uri) -> Unit): ActivityResultLauncher<String> {
    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { onResult(it) }
    }
}

@Composable
fun rememberCameraLauncher(onResult: (Uri) -> Unit): ActivityResultLauncher<Uri> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
        if (success) {
            val cameraOutputUri = createTempImageFile(context)?.toUri()
            cameraOutputUri?.let { uri ->
                onResult(uri)
            }
        }
    }
}

fun checkCameraPermission(activity: Activity, launcher: ActivityResultLauncher<Uri>) {
     val CAMERA_PERMISSION_REQUEST_CODE = 100
    when {
        ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
            takePhoto(activity, launcher)
        }
        else -> {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
    }
}
private fun createTempImageFile(context: Context): File? {
    return try {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    } catch (ex: IOException) {
        null
    }
}

fun checkStoragePermission(activity: Activity, launcher: ActivityResultLauncher<String>) {
    when {
        activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
            selectImageFromGallery(launcher)
        }
        else -> {
            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}
