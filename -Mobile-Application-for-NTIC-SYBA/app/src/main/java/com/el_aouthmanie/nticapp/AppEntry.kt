package com.el_aouthmanie.nticapp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.el_aouthmanie.nticapp.globals.CONSTANTS
import com.el_aouthmanie.nticapp.modules.OnlineDataBase
import com.el_aouthmanie.nticapp.ui.screens.anouncmentsScreen.AnnouncementsScreen
import com.el_aouthmanie.nticapp.ui.screens.homeScreen.HomeScreen
import com.el_aouthmanie.nticapp.ui.screens.launchingScreen.LaunchingScreen
import com.el_aouthmanie.nticapp.ui.screens.loginScreen.LoginScreen
import com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.ScheduleScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppEntry(
    context : Context,
    navController : NavHostController,
    dataBase : OnlineDataBase
) {
    var defaultDestination = CONSTANTS.Screens.LAUNCHING

    if (dataBase.isUserLoggedIn(context)){
        defaultDestination = CONSTANTS.Screens.HOME
    }




        NavHost(
            navController = navController,
            startDestination = defaultDestination
        )
        {

            composable(CONSTANTS.Screens.LAUNCHING){
                LaunchingScreen(
                    List(3){Pair("hheheh","it oeoeoeoeoeooeoeoe")}
                ){
                    navController.navigate(CONSTANTS.Screens.LOGING)
                }
            }
            composable(CONSTANTS.Screens.LOGING){
                LoginScreen(){ login , password ->
                    navController.navigate(CONSTANTS.Screens.HOME)

                }
            }
            composable(CONSTANTS.Screens.HOME){
                //todo : pass the nav to the home screen
                HomeScreen(
                    navController
                )
            }

            composable(CONSTANTS.Screens.SCHEDULE){
                ScheduleScreen()
            }
            composable(CONSTANTS.Screens.ANNOUNCMENTS){
                AnnouncementsScreen(navController)
            }
        }


}