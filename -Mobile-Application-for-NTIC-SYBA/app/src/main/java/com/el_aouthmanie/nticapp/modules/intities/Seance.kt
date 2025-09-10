package com.el_aouthmanie.nticapp.modules.intities
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import io.realm.kotlin.types.annotations.Index

open class Seance : RealmObject {
    @PrimaryKey
    var id: String = "N/A"

    var codeSeance: String = ""

    var dayName : String = "N/A"
        set(value) {
            field = value.lowercase()
        }

    @Index // Faster querying
    var nomMode: String = ""


    var classRoom: String = "--"
    var teacher: String = "Unknown"

    var intitule: String = ""
    var moduleDetails: String = "No details"

    @Index
    var startingTime: String = "00:00"
    var endingTime: String = "00:00"
}
