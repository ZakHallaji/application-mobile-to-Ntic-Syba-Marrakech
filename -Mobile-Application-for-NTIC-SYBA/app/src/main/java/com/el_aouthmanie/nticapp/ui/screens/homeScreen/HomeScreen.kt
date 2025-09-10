package com.el_aouthmanie.nticapp.ui.screens.homeScreen


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.el_aouthmanie.nticapp.R
import com.el_aouthmanie.nticapp.globals.CONSTANTS
import com.el_aouthmanie.nticapp.modules.OnlineDataBase
import com.el_aouthmanie.nticapp.modules.intities.Seance
import com.el_aouthmanie.nticapp.modules.realmHandler.RealmManager
import com.el_aouthmanie.nticapp.ui.screens.homeScreen.components.HeaderSection
import com.el_aouthmanie.nticapp.ui.screens.homeScreen.components.NotificationsList
import com.el_aouthmanie.nticapp.ui.screens.homeScreen.components.RectangleButtonWithIcon
import com.el_aouthmanie.nticapp.ui.screens.homeScreen.components.ScheduleCard
import com.el_aouthmanie.nticapp.ui.theme.backgroundWhite
import com.el_aouthmanie.nticapp.ui.theme.primaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val db = OnlineDataBase
    val scope = rememberCoroutineScope()

    var nextDayClass by remember { mutableStateOf(Seance()) }
    var i = remember { mutableStateOf(0) }

    scheduleUIUpdate(i)
    LaunchedEffect(i) {
        db.syncClasses("AM201", "S2S26", RealmManager.realm, scope, onFailureResponse = {
            val fetchedClass = db.getNextClass(RealmManager.realm, scope)
            nextDayClass = fetchedClass ?: Seance()
        }) {
            val fetchedClass = db.getNextClass(RealmManager.realm, scope)

            nextDayClass = fetchedClass ?: Seance()
        }
    }


    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        Modifier.background(backgroundWhite),
        topBar = { HeaderSection() },


        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState,

                snackbar = { message ->
                    Snackbar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        content = {
                            Text(text = message.visuals.message, textAlign = TextAlign.Center)
                        }
                    )
                }

            )
        },

//        floatingActionButton = {
//            FloatingActionButton(onClick = { exitProcess(0) }) {
//                Text(text = "aha")
//            }
//        }
    ) { paddingValues ->
        Box {
            Canvas(modifier = Modifier.fillMaxSize()) {

                //some lines , i think it absurde for now
                repeat(3) {
                    drawLine(
                        color = primaryBlue.copy(alpha = 0.1f),
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {


                // Image Slider
                HorizontalPager(
                    state = rememberPagerState {
                        3
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(16.dp)),

                    ) { i ->
                    ScheduleCard(nextDayClass)
                }
                // here

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RectangleButtonWithIcon(icon = Icons.Outlined.Star) {
                        navController.navigate(CONSTANTS.Screens.HOME)
                    }
                    RectangleButtonWithIcon(icon = Icons.Outlined.DateRange) {
                        navController.navigate(CONSTANTS.Screens.SCHEDULE)
                    }
                    RectangleButtonWithIcon(icon = Icons.Outlined.Notifications) {
                        navController.navigate(CONSTANTS.Screens.ANNOUNCMENTS)
                    }
                }


                NotificationsList(notifications = List(10) {
                    com.el_aouthmanie.nticapp.ui.screens.homeScreen.components.NotificationItem(
                        icon = R.drawable.profile,
                        subtitle = "hello worlsd",
                        title = "hello $it"
                    )
                })

            }
        }
    }
}

//
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun Hehe(modifier: Modifier = Modifier) {
    val a = rememberNavController()
    HomeScreen(a)


}

fun scheduleUIUpdate(i: MutableState<Int>) {
    val now = Calendar.getInstance()
    val target = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 18)
        set(Calendar.MINUTE, 22)
        set(Calendar.SECOND, 0)
    }

    if (now.after(target)) {
        target.add(Calendar.DAY_OF_MONTH, 1) // Move to next day if time has passed
    }

    val delayMillis = target.timeInMillis - now.timeInMillis

    CoroutineScope(Dispatchers.Main).launch {
        delay(delayMillis)
        i.value++
        Log.d("j", "feffefe")
    }

}
