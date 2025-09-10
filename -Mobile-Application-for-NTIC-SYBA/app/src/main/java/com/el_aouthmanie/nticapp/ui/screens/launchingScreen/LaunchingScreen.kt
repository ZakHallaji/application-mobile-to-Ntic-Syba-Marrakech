package com.el_aouthmanie.nticapp.ui.screens.launchingScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.ui.compenents.NextButton
import com.el_aouthmanie.nticapp.ui.theme.headerPrimary
import com.el_aouthmanie.nticapp.ui.theme.inactiveItemGray
import com.el_aouthmanie.nticapp.ui.theme.normalPadding
import com.el_aouthmanie.nticapp.ui.theme.primaryBlue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LaunchingScreen(
    screensTexts : List<Pair<String,String>> = emptyList(),
    onSkip : () -> Unit = {},
    onComplete : () -> Unit = {}
) {
    var currentPage by remember { mutableIntStateOf(0) }
    val totalPages = screensTexts.size
    val titles = screensTexts.map { it.first }
    val descriptions = screensTexts.map { it.second }

    // Animation states
    val buttonScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "wierd"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Skip button with fade animation
        AnimatedVisibility(
            visible = currentPage < totalPages - 1,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                TextButton(
                    onClick = { onSkip() },
                    modifier = Modifier
                        .graphicsLayer { scaleX = buttonScale; scaleY = buttonScale }
                ) {
                    Text("Skip", color = headerPrimary)
                }
            }
        }

        // Content centered vertically and horizontally
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Page title and description with crossfade and slide animation
            AnimatedContent(
                targetState = currentPage,
                transitionSpec = {
                    if (targetState > initialState) {
                        // Moving to next page
                        slideInHorizontally { width -> width } + fadeIn() with
                                slideOutHorizontally { width -> -width } + fadeOut()
                    } else {
                        // Moving to previous page
                        slideInHorizontally { width -> -width } + fadeIn() with
                                slideOutHorizontally { width -> width } + fadeOut()
                    }.using(SizeTransform(clip = false))
                }
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = titles[page],
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = descriptions[page],
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Bottom controls
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(normalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Page indicators with bounce animation
            Row(
                modifier = Modifier.padding(start = normalPadding)
            ) {
                repeat(totalPages) { index ->
                    val color by animateColorAsState(
                        targetValue = if (index == currentPage) primaryBlue else inactiveItemGray,
                        animationSpec = tween(durationMillis = 300)
                    )

                    val scale by animateFloatAsState(
                        targetValue = if (index == currentPage) 1.2f else 1f,
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(color)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                            .clickable { currentPage = index }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Next/Finish Button with scale animation
            AnimatedContent(
                targetState = currentPage,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }
            ) { page ->
                NextButton(){
                    if (page < totalPages - 1) {
                        currentPage++
                    } else {
                        // Finish logic
                        onComplete()
                    }
                }
            }
        }
    }
}
