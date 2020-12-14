package pl.kamilszustak.read.common.date

import java.util.*

object DateHelper {
    fun generateWeek(date: SimpleDate): Week {
        val calendar = Calendar.getInstance().apply {
            time = date.toDate()
        }
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            .let { if (it == 1) 7 else it - 1 }
        val startDay = date.addDays(1 - dayOfWeek)

        return Week(startDay)
    }
}