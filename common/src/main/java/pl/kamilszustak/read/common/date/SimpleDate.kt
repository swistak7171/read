package pl.kamilszustak.read.common.date

import androidx.annotation.CheckResult
import java.util.*

data class SimpleDate(
    val year: Int,
    val month: Int,
    val day: Int,
) : Comparable<SimpleDate> {

    override fun compareTo(other: SimpleDate): Int {
        return when {
            this == other -> 0

            this.year > other.year ||
                (this.year == other.year && this.month > other.month) ||
                (this.year == other.year && this.month == other.month && this.day > other.day) -> 1

            else -> -1
        }
    }

    @CheckResult
    fun addDays(days: Int): SimpleDate {
        return toCalendar()
            .apply { add(Calendar.DATE, days) }
            .date
    }

    fun toDate(): Date = toCalendar().time

    fun assignTo(calendar: Calendar) {
        calendar.set(year, month - 1, day)
    }

    fun toCalendar(): Calendar {
        return Calendar.getInstance().apply {
            assignTo(this)
        }
    }

    fun format(): String {
        val formattedMonth = addPadding(month)
        val formattedDay = addPadding(day)

        return buildString {
            append(year)
            append("-")
            append(formattedMonth)
            append("-")
            append(formattedDay)
        }
    }

    private fun addPadding(value: Int): String {
        return if (value < 10) {
            "0$value"
        } else {
            value.toString()
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

fun Date.toSimpleDate(): SimpleDate =
    SimpleDate.fromDate(this)