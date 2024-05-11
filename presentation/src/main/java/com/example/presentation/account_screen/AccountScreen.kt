package com.example.presentation.account_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.presentation.login_screen.common_item.TypeAccountBottomSheet
import com.example.presentation.main_screen.MainViewModel
import com.example.presentation.theme.GrayDarkest
import com.example.presentation.theme.GrayLighter
import com.example.presentation.theme.LightDarkGray
import com.example.presentation.theme.LightGray
import com.example.utils.Constants

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AccountScreen(
    mainViewModel: MainViewModel,
    toTermsConditionScreen: () -> Unit,
    toLoginScreen: () -> Unit
) {
    val userState by mainViewModel.currentUser.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showTypeAccountDialog by remember { mutableStateOf(false) }
    val typeAccount = remember { mutableStateOf(
        if (mainViewModel.isManager.value) Constants.TypeOfAccount.MANAGER
        else Constants.TypeOfAccount.USER)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = GrayDarkest,
        )

        HorizontalDivider( color = GrayLighter)

        userState?.let {
            Profile(
                imageProfile = mainViewModel.currentProfileImage.value,
                user = it,
                editIcon = { showDialog = true }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TypeOfAccount() { showTypeAccountDialog = true }
            TermsConditions { toTermsConditionScreen() }
            SignOut {
                mainViewModel.signOut()
                toLoginScreen()
            }
        }

        if (showTypeAccountDialog) {
            TypeAccountBottomSheet(
                currentTypeAccount = typeAccount.value,
                typeAccount = { typeAccount.value = it },
                showTypeAccountDialog = { showTypeAccountDialog = false }
            )
        }
        LaunchedEffect(typeAccount.value) {
            if (typeAccount.value == Constants.TypeOfAccount.MANAGER) {
                mainViewModel.updateUserStatus(true)
            } else {
                mainViewModel.updateUserStatus(false)
            }
        }

        if (showDialog) {
            EditProfileDialog(
                onDismiss = { showDialog = false },
                toTakePhoto = {
                    showDialog = false
                    mainViewModel.updateImageAccount(it)
                },
            )
        }
    }
}

@Composable
fun TypeOfAccount(toTypeOfAccount: () -> Unit) {
    Button(
        onClick = { toTypeOfAccount() },
        modifier = Modifier
            .fillMaxWidth()
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

@Composable
fun TermsConditions(toTermsConditions: () -> Unit) {
    Button(
        onClick = { toTermsConditions() },
        modifier = Modifier
            .fillMaxWidth()
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

@Composable
fun SignOut(toSignOut: () -> Unit) {
    Button(
        onClick = { toSignOut() },
        modifier = Modifier
            .fillMaxWidth()
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


@Composable
fun EditProfileDialog(
    onDismiss: () -> Unit,
    toTakePhoto: (Int) -> Unit
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
                ImageGrid(
                    images = listOf(
                        R.drawable.ic_profile,
                        R.drawable.ic_profile2,
                        R.drawable.ic_profile3,
                        R.drawable.ic_profile4,
                        R.drawable.ic_profile5,
                        R.drawable.ic_profile6

                    ),
                    onImageSelected = { it -> toTakePhoto(it) }
                )

            }
        }
    }
}

@Composable
fun ImageGrid(
    images: List<Int>,
    onImageSelected: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(images) { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clickable { onImageSelected(image) }
                    .clip(RectangleShape)
            )
        }
    }
}

//fun selectImageFromGallery(launcher: ActivityResultLauncher<String>) {
//    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//    launcher.launch(intent.toString())
//}
//
//fun takePhoto(context: Context, launcher: ActivityResultLauncher<Uri>) {
//    val values = ContentValues().apply {
//        put(MediaStore.Images.Media.TITLE, "New Photo")
//        put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
//    }
//    val photoUri =
//        context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//    photoUri?.let {
//        launcher.launch(it)
//    }
//}
//
//fun deletePhoto() {
//
//}
//
//
//@Composable
//fun rememberImagePickerLauncher(
//    contentResolver: ContentResolver,
//    onResult: (Bitmap) -> Unit
//): ActivityResultLauncher<String> {
//    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        uri?.let {
//            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
//            onResult(bitmap)
//        }
//    }
//}
//
//@Composable
//fun rememberCameraLauncher(onResult: (Bitmap) -> Unit): ActivityResultLauncher<Uri> {
//    val context = LocalContext.current
//    return rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
//        if (success) {
//            val cameraOutputUri = createTempImageFile(context)?.toUri()
//            cameraOutputUri?.let { uri ->
//                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
//                onResult(bitmap)
//            }
//        }
//    }
//}
//
//
//fun checkCameraPermission(activity: Activity, launcher: ActivityResultLauncher<Uri>) {
//    val CAMERA_PERMISSION_REQUEST_CODE = 100
//    when {
//        ContextCompat.checkSelfPermission(
//            activity,
//            Manifest.permission.CAMERA
//        ) == PackageManager.PERMISSION_GRANTED -> {
//            takePhoto(activity, launcher)
//        }
//
//        else -> {
//            ActivityCompat.requestPermissions(
//                activity,
//                arrayOf(Manifest.permission.CAMERA),
//                CAMERA_PERMISSION_REQUEST_CODE
//            )
//        }
//    }
//}
//
//private fun createTempImageFile(context: Context): File? {
//    return try {
//        val timeStamp: String =
//            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        File.createTempFile(
//            "JPEG_${timeStamp}_",
//            ".jpg",
//            storageDir
//        )
//    } catch (ex: IOException) {
//        null
//    }
//}
//
//fun checkStoragePermission(activity: Activity, launcher: ActivityResultLauncher<String>) {
//    when {
//        activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
//            selectImageFromGallery(launcher)
//        }
//
//        else -> {
//            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//    }
//}
