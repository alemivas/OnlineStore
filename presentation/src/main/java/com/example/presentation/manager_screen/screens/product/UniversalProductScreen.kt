package com.example.presentation.manager_screen.screens.product

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.Category
import com.example.presentation.manager_screen.common.ScreenType
import com.example.presentation.manager_screen.ui.ManagerTopAppBar
import com.example.presentation.manager_screen.ui.StyledTextField



@Composable
fun UniversalProductScreen(type: ScreenType) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp)
    ) {

        val id = remember { mutableStateOf(TextFieldValue("")) }
        val title = remember { mutableStateOf(TextFieldValue("")) }
        val price = remember { mutableStateOf(TextFieldValue("")) }
        val description = remember { mutableStateOf(TextFieldValue("")) }
        val images = remember { mutableStateOf(TextFieldValue("")) }
        val category = remember { mutableStateOf(TextFieldValue("")) }
        val categoryDropDown = remember { mutableStateOf(false) }
        val categories = remember {
            mutableStateOf<List<Category>>(
//            emptyList()
                listOf(
                    Category(
                        1,
                        "test",
                        "test",
                        "test",
                        "test"
                    ),
                    Category(
                        1,
                        "sgfgsfsdfsdfsdfsdfsdf",
                        "test",
                        "test",
                        "test"
                    )
                )
            )
        }

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
            
            ManagerTopAppBar("${type.label} ${type.model}")

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
                        "Title",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        title,
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
                        "Price",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        price,
                        modifier = maxWidth,
                        options = KeyboardOptions.Default
                            .copy(keyboardType = KeyboardType.Number)
                    )
                }

                Spacer(Modifier.height(16.dp))


                val ddModifier = Modifier
                    .clip(RoundedCornerShape(1f))
                    .clickable {
                        categoryDropDown.value = !categoryDropDown.value
                    }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = maxWidth
                ) {
                    Text(
                        "Category",
                        style = textStyle,
                        modifier = fill40width
                    )

                    Column(
                        modifier = maxWidth
                    ) {
                        StyledTextField(
                            category,
                            readOnly = true,
                            trailingIcon = {
                                when (categoryDropDown.value) {
                                    true -> Icon(
                                        Icons.Default.KeyboardArrowUp, null,
                                        modifier = ddModifier
                                    )

                                    false -> Icon(
                                        Icons.Default.KeyboardArrowDown, null,
                                        modifier = ddModifier
                                    )
                                }
                            },
                            modifier = maxWidth,
                            options = KeyboardOptions.Default
                                .copy(keyboardType = KeyboardType.Number)
                        )


//                Spacer(Modifier.height(8.dp))
                        val scrollState = rememberScrollState()
                        DropdownMenu(expanded = categoryDropDown.value,
                            scrollState = scrollState,
                            offset = DpOffset(0.dp, 8.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp)),
                            onDismissRequest = { categoryDropDown.value = false }) {
                            for (cat in categories.value) {
                                DropdownMenuItem(
                                    modifier = Modifier.height(40.dp),
                                    text = { Text(cat.name) },
                                    onClick = {
                                        category.value = TextFieldValue(cat.id.toString())
                                        categoryDropDown.value = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = maxWidth
                ) {
                    Text(
                        "Description",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        description,
                        minLines = 5,
                        maxLines = 5,
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
                        "Image url",
                        style = textStyle,
                        modifier = fill40width
                    )
                    StyledTextField(
                        images,
                        modifier = maxWidth
                    )
                }

                Spacer(Modifier.height(24.dp))
            }

            Button(
                onClick = when (type) {
                    is ScreenType.Create -> {
                        {
//                            apiService.create
                        }
                    }

                    is ScreenType.Update -> {
                        {
//                            apiService.update
                        }
                    }

                    is ScreenType.Delete -> {
                        {
//                            apiService.delete
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