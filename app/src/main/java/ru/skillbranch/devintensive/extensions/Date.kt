package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String =
        SimpleDateFormat(pattern, Locale("ru")).format(this)

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

//0с - 1с "только что"
//1с - 45с "несколько секунд назад"
//45с - 75с "минуту назад"
//75с - 45мин "N минут назад"
//45мин - 75мин "час назад"
//75мин - 22ч "N часов назад"
//22ч - 26ч "день назад"
//26ч - 360д "N дней назад"
//>360д "более года назад"

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = (Date().time - date.time) / 1000
    return when {
        diff <= 1 -> "только что"
        diff in 1..45 -> "несколько секунд назад"
        diff in 45..75 -> "минуту назад"
        diff in 75..45 * 60 -> "${diff / 60} ${if (diff / 60 == 1L) "минуту" else if (diff / 60 in 2L..4L) "минуты" else "минут"} назад"
        diff in 45 * 60..75 * 60 -> "час назад"
        diff in 75 * 60..22 * 60 * 60 -> "${diff / 60 / 60} часов назад"
        diff in 22 * 60 * 60..26 * 60 * 60 -> "день назад"
        diff in 26 * 60 * 60..360 * 60 * 60 * 24 -> "${diff / 60 / 60 / 24} дней назад"
        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}