package pl.kamilszustak.read.common.date

import java.util.*

data class SimpleDate(
    val year: Int,
    val month: Int,
    val day: Int,
) {
    fun toDate(): Date {
        val calendar = Calendar.getInstance().apply {
            set(year, month -1, day)
        }

        return calendar.time
    }

    companion object {
        fun fromCalendar(calendar: Calendar): SimpleDate {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            return SimpleDate(year, month, day)
        }

        fun fromDate(date: Date): SimpleDate {
            val calendar = Calendar.getInstance().apply {
                time = date
            }

            return fromCalendar(calendar)
        }

        fun current(): SimpleDate {
            val calendar = Calendar.getInstance()

            return fromCalendar(calendar)
        }
    }
}