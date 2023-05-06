package com.natiqhaciyef.greencamp.domain.util.functions

fun dateToLocalTime(formattedDate: String): String{
    val day = fromDateToDay(formattedDate.substring(0 until 2))
    val month = fromDateToMonth(formattedDate.substring(3 until 5))
    return "$day $month"
}

fun main() {
    dateToLocalTime("02.04.2023")
//    println("02.04.2023".substring(3 until 5))  // month
//    println("02.04.2023".substring(0 until 2))  // day
}

fun fromDateToMonth(month: String) = when(month){
    "01" -> { "Yanvar" }
    "02" -> { "Fevral" }
    "03" -> { "Mart" }
    "04" -> { "Aprel" }
    "05" -> { "May" }
    "06" -> { "Iyun" }
    "07" -> { "Iyul" }
    "08" -> { "Avqust" }
    "09" -> { "Sentyabr" }
    "10" -> { "Oktyabr" }
    "11" -> { "Noyabr" }
    "12" -> { "Dekabr" }
    else -> "Time left"
}

fun fromDateToDay(day: String) = if (day[0].toInt() == 0) day[1] else day