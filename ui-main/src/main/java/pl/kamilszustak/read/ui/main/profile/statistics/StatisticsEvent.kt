package pl.kamilszustak.read.ui.main.profile.statistics

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class StatisticsEvent : ViewEvent {
    object OnPreviousWeekButtonClicked : StatisticsEvent()
    object OnNextWeekButtonClicked : StatisticsEvent()
    object OnPreviousMonthButtonClicked : StatisticsEvent()
    object OnNextMonthButtonClicked : StatisticsEvent()
}
