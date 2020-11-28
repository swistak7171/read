package pl.kamilszustak.read.common.date

import androidx.annotation.StringRes
import pl.kamilszustak.read.common.R

enum class Month(
    val number: Int,
    @StringRes val nameResourceId: Int,
) : HasDays {

    JANUARY(1, R.string.january) {
        override fun getLength(year: Int): Int = 31
    },

    FEBRUARY(2, R.string.february) {
        override fun getLength(year: Int): Int =
            if (year % 4 == 0) 29 else 28
    },

    MARCH(3, R.string.march) {
        override fun getLength(year: Int): Int = 31
    },

    APRIL(4, R.string.april) {
        override fun getLength(year: Int): Int = 30
    },

    MAY(5, R.string.may) {
        override fun getLength(year: Int): Int = 31
    },

    JUNE(6, R.string.june) {
        override fun getLength(year: Int): Int = 30
    },

    JULY(7, R.string.july) {
        override fun getLength(year: Int): Int = 31
    },

    AUGUST(8, R.string.august) {
        override fun getLength(year: Int): Int = 31
    },

    SEPTEMBER(9, R.string.september) {
        override fun getLength(year: Int): Int = 30
    },

    OCTOBER(10, R.string.october) {
        override fun getLength(year: Int): Int = 31
    },

    NOVEMBER(11, R.string.november) {
        override fun getLength(year: Int): Int = 30
    },

    DECEMBER(12, R.string.december) {
        override fun getLength(year: Int): Int = 31
    };

    companion object {
        fun ofNumber(number: Int): Month {
            return values().find { it.number == number }
                ?: throw IllegalArgumentException("Invalid month number (not in 1-12 range): $number")
        }
    }
}