package com.el_aouthmanie.nticapp.ui.screens.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.el_aouthmanie.nticapp.modules.intities.Seance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SchedulePreview(modifier: Modifier = Modifier,
                             nextClass : Seance,
                             scope : CoroutineScope
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
    val moduleDetailsScroleState = rememberScrollState()
    Text(text = nextClass.startingTime + " - " + nextClass.endingTime,
        color = MaterialTheme.colorScheme.onPrimary,
//        modifier = Modifier.weight(1f)

    )
    VerticalDivider(
        modifier = Modifier
            .height(40.dp)
            .padding(horizontal = 10.dp)
    )
    Text(
        text = nextClass.moduleDetails,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .weight(2f)
            .verticalScroll(moduleDetailsScroleState)
    )
    Spacer(modifier = Modifier.weight(0.5f))

    // **Action Button**
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier

            .size(40.dp)
            .clickable {
                scope.launch {
//                                        val description =
//                                            db.getClassroomByName("A1")?.arDescription!!
//
//
//                                        snackbarHostState.showSnackbar(
//                                            message = description,
//                                            duration = SnackbarDuration.Long,
//                                            actionLabel = "hello"
//                                        )


                }
            }
    ) {
        Box(contentAlignment = Alignment.Center) {
            var clazz = nextClass.classRoom

            if (clazz == "null") clazz = "DIS"
            Text(text = clazz , color = MaterialTheme.colorScheme.onPrimary)
        }

    }

    }
}

@Preview
@Composable
private fun scheduleScreen() {

    SchedulePreview(nextClass = Seance().apply {
        this.endingTime = "18:30"
        this.startingTime = "13:30"
        this.moduleDetails = "Lorem ipsum dolor sit amet, consetetur sadips sadipssadipssadipssadipssadipssadipssadips"
    }, scope = rememberCoroutineScope())
}