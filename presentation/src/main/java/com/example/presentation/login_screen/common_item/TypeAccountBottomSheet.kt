package com.example.presentation.login_screen.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.Mint
import com.example.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeAccountBottomSheet(
    typeAccount: (Constants.TypeOfAccount) -> Unit,
    showTypeAccountDialog: (Boolean) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val types = listOf(Constants.TypeOfAccount.USER, Constants.TypeOfAccount.MANAGER)

    val password = remember { mutableStateOf("") }
    var isErrorPassword by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { showTypeAccountDialog(false) },
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Type of account",
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = GrayDark
            )

            SingleChoiceSegmentedButtonRow {
                types.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = types.size),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex
                    ) {
                        Text(label.name)
                    }
                }
            }

            if (selectedIndex == 1) {
                PasswordScreenTextField(
                    header = "Manager password",
                    placeholder = "Enter default manager password",
                    value = password.value,
                    newValue = { password.value = it },
                    isHidden = true,
                    isError = isErrorPassword
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Mint),
                onClick = {
                    if (selectedIndex == 0) {
                        typeAccount(types[selectedIndex])
                        showTypeAccountDialog(false)
                    } else {
                        if (Constants.DEFAULT_MANAGER_PASSWORD == password.value && selectedIndex == 1) {
                            typeAccount(types[selectedIndex])
                            isErrorPassword = false
                            showTypeAccountDialog(false)
                        } else {
                            isErrorPassword = true
                        }
                    }
                }
            ) {
                Text(text = "Continue")
            }
        }
    }
}