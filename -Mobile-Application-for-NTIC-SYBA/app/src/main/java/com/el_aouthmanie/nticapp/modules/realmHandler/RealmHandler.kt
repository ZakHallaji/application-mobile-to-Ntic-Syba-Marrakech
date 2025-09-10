package com.el_aouthmanie.nticapp.modules.realmHandler

import android.content.Context
import com.el_aouthmanie.nticapp.modules.intities.Notification
//import com.el_aouthmanie.istanticapp.modules.intities.DayPlaning
import com.el_aouthmanie.nticapp.modules.intities.Seance
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


object RealmManager {
    lateinit var realm: Realm

    fun initialize() {
        if (!::realm.isInitialized) {
            val config = RealmConfiguration.Builder(
                setOf(
                    Seance::class,
                    Notification::class
                ))
                .name("day_planning.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()

            realm = Realm.open(config)
        }
    }
}
