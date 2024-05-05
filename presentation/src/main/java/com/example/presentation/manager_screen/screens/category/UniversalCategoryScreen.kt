package com.example.presentation.manager_screen.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.CategoryRequest
import com.example.presentation.manager_screen.ManagerViewModel
import com.example.presentation.manager_screen.common.ScreenType
import com.example.presentation.manager_screen.ui.ManagerTopAppBar
import com.example.presentation.manager_screen.ui.StyledTextField

@Composable
fun UniversalCategoryScreen(
    managerViewModel: ManagerViewModel = hiltViewModel(),
    type: ScreenType,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp)
    ) {

        val id = remember { mutableStateOf(TextFieldValue("")) }
        val name = remember { mutableStateOf(TextFieldValue("")) }
        val image = remember { mutableStateOf(TextFieldValue("")) }

        val maxWidth = Modifier
            .fillMaxWidth()
//            .height(40.dp)

        val textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        val fill40width = Modifier.fillMaxWidth(0.33f)

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            ManagerTopAppBar("${type.label} ${type.model}") { onBackClick() }

            if (type !is ScreenType.Create) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = maxWidth
                ) {
                    Text(
                        "Id",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        id,
                        modifier = maxWidth,
                        options = KeyboardOptions.Default
                            .copy(keyboardType = KeyboardType.Number)
                    )
                }

                Spacer(Modifier.height(16.dp))
            }

            if (type !is ScreenType.Delete) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = maxWidth
                ) {
                    Text(
                        "Name",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        name,
                        modifier = maxWidth
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = maxWidth
                ) {
                    Text(
                        "Image",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        image,
                        modifier = maxWidth
                    )
                }
            }

            Spacer(Modifier.height(24.dp))


            Button(
                onClick = when (type) {
                    is ScreenType.Create -> {
                        {
//                            apiService.create
                            managerViewModel.createNewCategory(CategoryRequest(
                                name = name.value.text,
                                image = image.value.text
                            ))
                        }
                    }

                    is ScreenType.Update -> {
                        {
//                            apiService.update
                            managerViewModel.updateCategory(
                                id = id.value.text.toInt(),
                                CategoryRequest(
                                    name = name.value.text,
                                    image = image.value.text
                                )
                            )
                        }
                    }

                    is ScreenType.Delete -> {
                        {
//                            apiService.delete
                            managerViewModel.deleteCategory(id.value.text.toInt())
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "${type.label} ${type.model}")
            }
        }
    }
}