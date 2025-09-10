package com.el_aouthmanie.nticapp.ui.compenents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomBottomAppBar() {
    var selected by remember { mutableIntStateOf(1) }

    val items = listOf(
        Triple("Home", Icons.Filled.Home, Color.White),
        Triple("Schedule", Icons.Filled.DateRange, Color.White),
        Triple("Announcements", Icons.Filled.Notifications, Color.White)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color(0xFF4A89DC)), // Custom blue background
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = item.second,
                        contentDescription = item.first,
                        tint = if (selected == index) Color.Black else Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = item.first,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selected == index) Color.Black else Color.White
                    )
                }
            }
        }
    }
}
