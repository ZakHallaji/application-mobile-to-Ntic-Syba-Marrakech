package com.el_aouthmanie.nticapp.ui.screens.homeScreen.components

import NotificationCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun NotificationsList(notifications: List<NotificationItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Today",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp)
        )

        LazyColumn {
            items(notifications) { notification ->
                NotificationCard(
                    icon = painterResource(id = notification.icon),
                    title = notification.title,
                    subtitle = notification.subtitle,
                    backgroundColor = notification.backgroundColor
                )
            }
        }
    }
}

data class NotificationItem(
    val icon: Int,
    val title: String,
    val subtitle: String,
    val backgroundColor: Color = Color.White
)
