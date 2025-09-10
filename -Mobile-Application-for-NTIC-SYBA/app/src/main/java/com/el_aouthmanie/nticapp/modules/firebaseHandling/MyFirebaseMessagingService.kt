package com.el_aouthmanie.nticapp.modules.firebaseHandling

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.el_aouthmanie.nticapp.R
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "Message received: ${remoteMessage.data}")
        remoteMessage.notification?.let {
            Log.d("FCM", "Notification Title: ${it.title}, Body: ${it.body}")
            showNotification(it.title, it.body)
        }
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("dddddddddddddddj",token)
        FirebaseMessaging.getInstance().subscribeToTopic("all")
    }

    private fun showNotification(title: String?, message: String?) {
        val builder = NotificationCompat.Builder(this, "student_channel")
            .setSmallIcon(R.drawable.event)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(30, builder.build())
    }

}
//class MyFirebaseMessagingService : FirebaseMessagingService() {
//
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//
//        Log.d("FCM", "Message received: ${message.notification?.body ?: "any"}")
//    }
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        Log.d("FCM", "TOKEN : $token")
//        FirebaseMessaging.getInstance().subscribeToTopic("all")
//
//    }
//
//}