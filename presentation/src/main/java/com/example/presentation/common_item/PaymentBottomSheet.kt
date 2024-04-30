package com.example.presentation.common_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.presentation.R
import com.example.presentation.theme.GrayDark
import com.example.presentation.theme.Mint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentBottomSheet(
    showBottomSheet: (Boolean) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet(false) },
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.animation_success)
            )
            val logoAnimationState =
                animateLottieCompositionAsState(composition = composition)

            LottieAnimation(
                modifier = Modifier.fillMaxWidth(),
                composition = composition,
                progress = { logoAnimationState.progress },
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Congrats! your payment\n is successfully",
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = GrayDark
            )

            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Mint),
                onClick = { showBottomSheet(false) }
            ) {
                Text(text = "Continue")
            }
        }
    }
}