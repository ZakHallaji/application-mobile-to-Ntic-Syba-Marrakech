package com.el_aouthmanie.nticapp


import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.work.impl.utils.forName
import com.el_aouthmanie.nticapp.modules.OnlineDataBase

import com.el_aouthmanie.nticapp.modules.realmHandler.RealmManager
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    private lateinit var dataBase: OnlineDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeFirebaseMessaging()
        requestNotificationPermission()
        RealmManager.initialize()
        createNotificationChannel()
        dataBase = OnlineDataBase

        setContent {
            val mainNavController = rememberNavController()
            val context = LocalContext.current

            AppEntry(
                context,
                mainNavController,
                dataBase
            )
        }
    }

    /**
     * Initializes Firebase Cloud Messaging and logs the token.
     */
    private fun initializeFirebaseMessaging() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { token ->
                    Log.d("FCM", "Token retrieved successfully: $token")
                } ?: Log.w("FCM", "FCM Token is null")
            } else {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
            }
        }
    }

    /**
     * Requests notification permissions for Android 13 (API 33) and above.
     */
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    /**
     * Creates a notification channel for API 26+.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "student_channel",
                "Student Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for student notifications"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }


}

@Preview(device = Devices.TABLET)
@Composable
fun helloWorld(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val c = rememberNavController()
    val d = OnlineDataBase
    AppEntry(context = context, navController = c, dataBase = d)
}
