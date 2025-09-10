package com.el_aouthmanie.nticapp.ui.compenents

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.ui.theme.buttonText
import com.el_aouthmanie.nticapp.ui.theme.normalButtonHeigt
import com.el_aouthmanie.nticapp.ui.theme.normalPadding
import com.el_aouthmanie.nticapp.ui.theme.primaryBlue
import com.el_aouthmanie.nticapp.ui.theme.smallPadding
import com.el_aouthmanie.nticapp.ui.theme.truWhite

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    text: String = "Next",
    folded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val buttonShape = CircleShape
    val defaultHeight = normalButtonHeigt

    FloatingActionButton(
        onClick = {
            onClick()
        },
        shape = FloatingActionButtonDefaults.largeShape,
        modifier = modifier,
//                        .padding(16.dp)
        containerColor = primaryBlue
    ) {
        // Animate content changes
        AnimatedContent(
            targetState = Unit, // You can use a state here if needed
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            }
        ) { _ ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = if(!folded){
                    Modifier.padding(start = normalPadding, end = 5.dp).fillMaxWidth()
                } else {
                    Modifier
                }
            ) {
                if(!folded){
                    Text(
                        text = text,
                        color = truWhite,
                        maxLines = 1,
                        fontSize = buttonText,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                }


                // Icon with bounce animation
                val infiniteTransition = rememberInfiniteTransition()
                val bounce by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 8f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    ), label = ""
                )
                Box (Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(if (!folded) truWhite else Color.Transparent)
                    .padding(smallPadding)

                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow Forward",
                        tint = if (!folded) primaryBlue else truWhite,
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = bounce.dp)
                            .graphicsLayer {
                                scaleX = 1.2f
                                scaleY = 1.2f
                            }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun buttonNext() {
    NextButton(folded = true)
}