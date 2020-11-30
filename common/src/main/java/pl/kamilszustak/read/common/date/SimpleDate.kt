package pl.kamilszustak.read.common.date

import java.util.*

data class SimpleDate(
    val year: Int,
    val month: Int,
    val day: Int,
) {
    fun toDate(): Date = toCalendar().time

    fun assignTo(calendar: Calendar) {
        calendar.set(year, month - 1, day)
    }

    fun toCalendar(): Calendar {
        return Calendar.getInstance().apply {
            assignTo(this)
        }
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

var Calendar.date: SimpleDate
    get() = SimpleDate.fromCalendar(this)
    set(value) {
        value.assignTo(this)
    }