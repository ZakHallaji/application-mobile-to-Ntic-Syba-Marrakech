package com.el_aouthmanie.nticapp.ui.screens.homeScreen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.el_aouthmanie.nticapp.ui.theme.primaryBlue

@Composable
fun RectangleButtonWithIcon(
    modifier: Modifier = Modifier,
    icon : ImageVector,
    containerColor: Color = primaryBlue,
    onClick : () -> Unit
) {

    FloatingActionButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .size(80.dp)

            .padding(6.dp),
        containerColor = containerColor

    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .fillMaxSize(0.6f)
        )
    }
}