//package com.el_aouthmanie.nticapp
//
//import EventsScreen
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideOutHorizontally
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.navigationBarsPadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//
//import androidx.compose.material3.DrawerState
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalDrawerSheet
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.PermanentNavigationDrawer
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.el_aouthmanie.nticapp.modules.intities.Notification
//import com.el_aouthmanie.nticapp.ui.screens.homeScreen.HomeScreen
//import com.el_aouthmanie.nticapp.ui.screens.DocumentsScreen
//import com.el_aouthmanie.nticapp.ui.screens.DocumentsViewModel
//import com.el_aouthmanie.nticapp.ui.screens.loginScreen.LoginScreen
//import com.el_aouthmanie.nticapp.ui.screens.anouncmentsScreen.NotificationScreen
//import com.el_aouthmanie.nticapp.ui.screens.launchingScreen.LaunchingScreen
//import com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.ScheduleScreen
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun MainScreen(
////    db: DatabaseHelper,
//) {
//
//
//    val navController = rememberNavController()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
//    var startDestination = "home"
//
//    // Get screen configuration
//    val configuration = LocalConfiguration.current
//    val isTablet = configuration.isLayoutSizeAtLeast(600)
////    if (authController.isUserLoggedIn(context)) {
////        startDestination = "home"
////    }
//
//    val gestureDetection = remember { mutableStateOf(true) }
//
//    if (isTablet) {
//        PermanentNavigationDrawer(
//            drawerContent = {
//                Surface(
//                    modifier = Modifier
//                        .width(240.dp)
//                        .fillMaxHeight(),
//                    color = MaterialTheme.colorScheme.surface,
//
//                ) {
//                    DrawerContent(
//                        navController = navController,
//                        scope = scope,
//                        drawerState = drawerState,
//                        isTablet = isTablet
//                    )
//                }
//            }
//        ) {
//            NavHostContent(navController, gestureDetection, startDestination)
//        }
//    } else {
//        ModalNavigationDrawer(
//            drawerState = drawerState,
//            drawerContent = {
//                ModalDrawerSheet {
//                    DrawerContent(
//                        modifier = Modifier.fillMaxWidth(0.7f),
//                        navController = navController,
//                        scope = scope,
//                        drawerState = drawerState,
//                        isTablet = isTablet
//                    )
//                }
//            },
//            gesturesEnabled = gestureDetection.value,
//            scrimColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
//        ) {
//            NavHostContent(navController, gestureDetection, startDestination)
//        }
//    }
//}
//
//@Composable
//private fun DrawerContent(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    scope: CoroutineScope,
//    drawerState: DrawerState,
//    isTablet: Boolean
//) {
//    Column(
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            "ISTA NTIC SYBA",
//            modifier = Modifier
//                .padding(16.dp)
//                .navigationBarsPadding()
//        )
//        HorizontalDivider()
//        DrawerItem("Home",
//            Icons.Default.Home,
//            navController) {
//            if (!isTablet) {
//                scope.launch { drawerState.close() }
//            }
//        }
//        DrawerItem("Schedule",
//            Icons.Filled.DateRange,
//            navController) {
//            if (!isTablet) scope.launch { drawerState.close() }
//        }
//        DrawerItem("events",
//            Icons.Default.Star,
//            navController) {
//            if (!isTablet) scope.launch { drawerState.close() }
//        }
//        DrawerItem("announcements",
//            Icons.Default.Notifications,
//            navController) {
//            if (!isTablet) scope.launch { drawerState.close() }
//        }
//        DrawerItem("Settings",
//            Icons.Default.Settings,
//            navController) {
//            if (!isTablet) scope.launch { drawerState.close() }
//        }
//        DrawerItem("aboutUs",
//            Icons.Default.Info,
//            navController) {
//            if (!isTablet) scope.launch { drawerState.close() }
//        }
//        DrawerItem("documents",
//            Icons.Default.Edit,
//            navController) {
//            if (!isTablet) scope.launch { drawerState.close() }
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//private fun NavHostContent(
//    navController: NavHostController,
//    gestureDetection: MutableState<Boolean>,
//
//
//    startDestination: String
//) {
//    val announcements = emptyList<Notification>()
////    val announcements = listOf(
//////        com.el_aouthmanie.nticapp.ui.screens.anouncmentsScreen.Announcement("Admin", "New udpdate available!", "10:30 AM"),
//////        com.el_aouthmanie.nticapp.ui.screens.anouncmentsScreen.Announcement("Support", "Server maintenance scheduled.Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata", "Yesterday"),
//////        com.el_aouthmanie.nticapp.ui.screens.anouncmentsScreen.Announcement("System", "Security patch applied successfully.", "Monday")
////    )
//    val a= LocalContext.current
//    NavHost(
//        navController = navController,
//
//        startDestination = startDestination,
//        enterTransition = {
//            slideInHorizontally(initialOffsetX = { 300 }) + fadeIn(
//                animationSpec = tween(durationMillis = 200)
//            )
//        },
//        exitTransition = { slideOutHorizontally(targetOffsetX = { -300 }) + fadeOut() }
//    ) {
//
//        composable("home") {
//            gestureDetection.value = true
//            HomeScreen()
//        }
//        val a = mapOf(
//            "helddlo" to "Lorem dipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod",
//            "heldldo" to "Lorem ipsum dolor sidt amet, consetetur sadipscing elitr, sed diam nonumy eirmod",
//            "hedlldo" to "Loredm dipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod",
//        ).entries.map { Pair(it.key,it.value) }
//        composable("announcements") { NotificationScreen(navController,announcements  = announcements) }
//        composable("schedule") { ScheduleScreen() }
//        composable("aboutUs") { LaunchingScreen() }
//        composable("events") { EventsScreen() }
//        composable("documents") { DocumentsScreen(DocumentsViewModel()) }
//        composable("login") {
//            LoginScreen(gestureDetection) { _, _ ->
////                authController.saveLoginState(a, true)
//                navController.navigate("home")
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DrawerItem(
//    label: String,
//    icon : ImageVector,
//    navController: NavController,
//    onClick: () -> Unit) {
//    AnimatedVisibility(visible = true) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(20.dp))
//                .clickable {
//                    navController.navigate(label) { launchSingleTop = true }
//                    onClick()
//                }
//                .padding(16.dp)
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = "$label icon",
//                modifier = Modifier.padding(end = 16.dp)
//            )
//            Text(text = label, modifier = Modifier.align(Alignment.CenterVertically))
//        }
//    }
//}
//
