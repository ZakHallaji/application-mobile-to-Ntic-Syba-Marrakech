package com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RowScope.DayText(day: String, pagerState: PagerState? = null, index: Int) {
    val isSaturday = day.lowercase() == "sun"
    val isSelected = pagerState?.currentPage == index

    val selectedColor = Color(0xFF4CAF50)
    val unselectedColor = Color(0xFFEEEEEE)
    val textColor = Color(0xFF333333)


    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else textColor,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    val scale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(isSelected) {
        if (isSelected) {
            scale.animateTo(1.1f, animationSpec = tween(200))
            scale.animateTo(1f, animationSpec = tween(200))
        }
    }

    Surface(
        color = animatedBackgroundColor,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = if (isSelected) 8.dp else 2.dp,
        modifier = Modifier
            .padding(4.dp)
            .weight(1f)
            .height(50.dp)
            .scale(scale.value)
            .clickable {
                pagerState?.let {
                    scope.launch { it.animateScrollToPage(index) }
                }
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.uppercase(),
                color = animatedTextColor,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}