package com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.el_aouthmanie.nticapp.R
import com.el_aouthmanie.nticapp.modules.intities.ClassBundle
import com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.dayIndex


@Composable
fun ClassList(pagerState: PagerState, courses: MutableSet<ClassBundle>) {
    HorizontalPager(state = pagerState) { pageIndex ->
        val selectedDay = dayIndex.keys.elementAt(pageIndex) // Get the day name by index
        val day = dayIndex[selectedDay]
        val filteredCourses = courses.filter { it.day == day } // Filter courses by day

        if (filteredCourses.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    ,
                Alignment.Center,

                ){

                AnimatedVisibility(visible = true) {
                    slideInVertically{ 3 }
                    fadeIn(initialAlpha = 0f)
                    Text("${stringResource(id = R.string.no_class)} $selectedDay",
                        textAlign = TextAlign.Companion.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                        )
                }
            }
        } else {
            val colState = rememberLazyListState()
            LazyColumn(modifier = Modifier.fillMaxSize(),
                state = colState
                )
            {

                items(filteredCourses) { course ->
                    AnimatedVisibility(
                        visible = true,
                        enter = slideInVertically(initialOffsetY = { it }, animationSpec = tween(durationMillis = 300)),
                        exit = slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(durationMillis = 300)),
                        modifier = Modifier.animateItem()
                    ) {
                        ClassItem(course)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

            }
        }
    }
}

