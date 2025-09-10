package com.el_aouthmanie.nticapp.ui.screens.homeScreen.components
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.ui.theme.primaryBlue


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import com.el_aouthmanie.nticapp.modules.intities.Seance

import kotlin.random.Random

@Composable
fun ScheduleCard(
    seance: Seance
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        colors = CardDefaults.outlinedCardColors().copy(containerColor = Color(0xFF1E88E5)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Background decorations with random positions and sizes
            Canvas(modifier = Modifier.fillMaxSize()) {
                repeat(5) {
                    drawCircle(
                        color = Color.White.copy(alpha = Random.nextFloat() * 0.3f),
                        radius = Random.nextFloat() * 100f + 30f,
                        center = center.copy(
                            x = Random.nextFloat() * size.width,
                            y = Random.nextFloat() * size.height
                        )
                    )
                }
                repeat(3) {
                    drawLine(
                        color = Color.White.copy(alpha = 0.2f),
                        start = center.copy(

                            x = Random.nextFloat() * size.width,
                            y = Random.nextFloat() * size.height
                        ),
                        end = center.copy(
                            x = Random.nextFloat() * size.width,
                            y = Random.nextFloat() * size.height
                        ),
                        strokeWidth = Random.nextFloat() * 6f + 2f
                    )
                }
            }

            // Time range at the top
            Text(
                text = "${seance.startingTime} - ${seance.endingTime}",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // Title and subtitle
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 32.dp)
            ) {
                val moduleTExtScrollState = rememberScrollState()
                Text(
                    text = seance.teacher,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = seance.moduleDetails + "\n" + seance.nomMode,
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.verticalScroll(moduleTExtScrollState)
                )
            }

            // Room number badge
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.CenterEnd)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = seance.classRoom,
                    color = Color.Blue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun Hehse(modifier: Modifier = Modifier) {
    ScheduleCard(Seance())


}