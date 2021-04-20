package pl.kamilszustak.read.ui.main.profile.statistics

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class StatisticsEvent : ViewEvent {
    object OnPreviousWeekButtonClicked : StatisticsEvent()
    object OnNextWeekButtonClicked : StatisticsEvent()
    object OnPreviousMonthButtonClicked : StatisticsEvent()
    object OnNextMonthButtonClicked : StatisticsEvent()
    object OnPreviousYearButtonClicked : StatisticsEvent()
    object OnNextYearButtonClicked : StatisticsEvent()
    object OnWeeklyStatisticsChartSwipedLeft : StatisticsEvent()
    object OnWeeklyStatisticsChartSwipedRight : StatisticsEvent()
    object OnMonthlyStatisticsChartSwipedLeft : StatisticsEvent()
    object OnMonthlyStatisticsChartSwipedRight : StatisticsEvent()
    object OnYearlyStatisticsChartSwipedLeft : StatisticsEvent()
    object OnYearlyStatisticsChartSwipedRight : StatisticsEvent()
}
