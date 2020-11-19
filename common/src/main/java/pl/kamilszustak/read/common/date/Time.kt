package pl.kamilszustak.read.common.date

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Time(
    val hour: Int,
    val minute: Int,
    val format: TimeFormat = TimeFormat.H24,
) : Parcelable {

    fun toEarlier(time: Time): Long {
        val current = Calendar.getInstance().apply {
            val hourField = getHourField(format)
            set(hourField, hour)
            set(Calendar.MINUTE, minute)
        }

        val later = Calendar.getInstance().apply {
            if (time.hour > hour || (time.hour == hour && time.minute > minute)) {
                add(Calendar.DAY_OF_MONTH, -1)
            }

            val hourField = getHourField(time.format)
            set(hourField, time.hour)
            set(Calendar.MINUTE, time.minute)
        }

        return (current.timeInMillis - later.timeInMillis)
    }

    fun toLater(time: Time): Long {
        val current = Calendar.getInstance().apply {
            val hourField = getHourField(format)
            set(hourField, hour)
            set(Calendar.MINUTE, minute)
        }

        val later = Calendar.getInstance().apply {
            if (time.hour < hour || (time.hour == hour && time.minute < minute)) {
                add(Calendar.DAY_OF_MONTH, 1)
            }

            val hourField = getHourField(time.format)
            set(hourField, time.hour)
            set(Calendar.MINUTE, time.minute)
        }

        return (later.timeInMillis - current.timeInMillis)
    }

    fun format(): String {
        return buildString {
            if (hour < 10) {
                append("0")
            }
            append(hour)
            append(":")
            if (minute < 10) {
                append("0")
            }
            append(minute)
        }
    }

    companion object {
        private fun getHourField(format: TimeFormat): Int {
            return when (format) {
                TimeFormat.H12 -> Calendar.HOUR
                TimeFormat.H24 -> Calendar.HOUR_OF_DAY
            }
        }

        fun current(format: TimeFormat = TimeFormat.H24): Time {
            val calendar = Calendar.getInstance()
            val hourField = getHourField(format)
            val hour = calendar.get(hourField)
            val minute = calendar.get(Calendar.MINUTE)

            return Time(hour, minute, format)
        }
    }
}

