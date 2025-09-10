package com.el_aouthmanie.nticapp.modules.intities

import androidx.compose.ui.graphics.Color


// this is just a wrapper for Seance class
data class ClassBundle(

    val name: String,
    val chapter: String,
    val room: String,
    val teacher: String,
    val bgColor: Color,

    val day: String,
    val startingHour: String,
    val endingHour: String
)