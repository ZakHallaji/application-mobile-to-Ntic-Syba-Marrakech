package com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.modules.scheduleHandler.DateHelper


@Composable
fun HeaderSection(pagerState: PagerState, message : String = "") {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Date Text
        Text(
            text = DateHelper.getCurrentDate().toString(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge // Apply material typography
        )

        // Day Text
        Text(
            text = DateHelper.getCurrentDayFormatted(),
            fontSize = 14.sp,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium // Apply material typography
        )

        // "Outdated" Text (aligned right and styled)
        Text(
            text = message,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
            color = MaterialTheme.colorScheme.error, // Use material design's error color
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.End
        )

        // Horizontal Divider
        HorizontalDivider(Modifier.padding(vertical = 10.dp))

        // Spacer for spacing between elements
        Spacer(modifier = Modifier.height(8.dp))

        // Weekdays Row
        val weekDays = DateHelper.getWeekDays()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Spread the days across the row
        ) {
            weekDays.forEachIndexed { index, day ->
                DayText(day = day, pagerState = pagerState, index = index)
            }
        }

        // Another Divider
        HorizontalDivider(Modifier.padding(vertical = 10.dp))
    }
}
