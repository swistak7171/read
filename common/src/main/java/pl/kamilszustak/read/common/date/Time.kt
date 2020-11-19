package pl.kamilszustak.read.common.date

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Time(
    val hour: Int,
    val minute: Int,
) : Parcelable

fun currentTime(format: TimeFormat = TimeFormat.H24): Time {
    val calendar = Calendar.getInstance()
    val hour = when (format) {
        TimeFormat.H12 -> calendar.get(Calendar.HOUR)
        TimeFormat.H24 -> calendar.get(Calendar.HOUR_OF_DAY)
    }
    val minute = calendar.get(Calendar.MINUTE)

    return Time(hour, minute)
}