package com.example.presentation.common_item

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAnimation(
    isCategory: Boolean
) {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    if (isCategory) {
        ShimmerItemCategory(brush = brush)
    } else {
        ShimmerItemProduct(brush = brush)
    }
}

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.9f)
)

@Composable
fun ShimmerItemCategory(
    brush: Brush
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(brush = brush, shape = RoundedCornerShape(12.dp))
    )
}

@Composable
fun ShimmerItemProduct(
    brush: Brush
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(brush = brush, shape = RoundedCornerShape(12.dp))
    )
}