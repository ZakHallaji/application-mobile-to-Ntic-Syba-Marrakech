package com.el_aouthmanie.nticapp.modules.scheduleHandler

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek
import java.util.Locale

object DateHelper {

    private val today: LocalDate = LocalDate.now()


    fun getCurrentDayFormatted(): String {
        val formatter = DateTimeFormatter.ofPattern("EEE MMM yyyy", Locale.getDefault())
        return today.format(formatter)
    }


    fun getCurrentDate(): Int = today.dayOfMonth

    fun getWeekDays(): List<String> {
        return DayOfWeek.entries.map { it.name.take(3) }
    }

    fun getWeekDates(): Map<String, Int> {
        val startOfWeek = today.with(DayOfWeek.MONDAY)
        return getWeekDays().mapIndexed { index, day ->
            day to startOfWeek.plusDays(index.toLong()).dayOfMonth
        }.toMap()
    }
}
