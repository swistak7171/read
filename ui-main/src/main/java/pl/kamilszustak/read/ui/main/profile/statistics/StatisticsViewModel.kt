package pl.kamilszustak.read.ui.main.profile.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.date.DateHelper
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.common.date.Week
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveMonthlyReadingStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveReadPagesStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveWeeklyReadingStatisticsUseCase
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val observeReadPagesStatistics: ObserveReadPagesStatisticsUseCase,
    private val observeWeeklyReadingStatistics: ObserveWeeklyReadingStatisticsUseCase,
    private val observeMonthlyReadingStatistics: ObserveMonthlyReadingStatisticsUseCase,
) : BaseViewModel<StatisticsEvent, StatisticsAction>() {

    private var weekDate: SimpleDate = SimpleDate.current()
        set(value) {
            field = value
            _isNextWeekButtonEnabled.value = (value != currentWeekDate)
        }

    private var monthDate: SimpleDate = SimpleDate.current()
        set(value) {
            field = value
            _isNextMonthButtonEnabled.value = (value != currentMonthDate)
        }

    private val currentWeekDate: SimpleDate = SimpleDate.current()
    private val currentMonthDate: SimpleDate = SimpleDate.current()

    val readPagesText: LiveData<String>
        get() = _readPagesStatistics.map { statistics ->
            if (statistics != null) {
                buildString {
                    append(statistics.first)
                    append(" / ")
                    append(statistics.second)
                }
            } else {
                "0 / 0"
            }
        }

    val readBooksText: LiveData<String>


    private val _weekText: MutableLiveData<String> = UniqueLiveData()
    val weekText: LiveData<String>
        get() = _weekText

    private val _monthText: MutableLiveData<String> = UniqueLiveData()
    val monthText: LiveData<String>
        get() = _monthText

    private val _isNextWeekButtonEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val isNextWeekButtonEnabled: LiveData<Boolean>
        get() = _isNextWeekButtonEnabled

    private val _isNextMonthButtonEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val isNextMonthButtonEnabled: LiveData<Boolean>
        get() = _isNextMonthButtonEnabled

    private val _readPagesStatistics: MutableLiveData<Pair<Int, Int>> = UniqueLiveData()
    val readPagesStatistics: LiveData<Pair<Float, Float>>
        get() = _readPagesStatistics.map { statistics ->
            statistics.first.toFloat() to statistics.second.toFloat()
        }

    private val _weeklyStatistics: MutableLiveData<Map<SimpleDate, Int>> = UniqueLiveData()
    val weeklyStatistics: LiveData<Map<String, Int>>
        get() = _weeklyStatistics.map { statistics ->
            statistics.mapKeys { entry ->
                entry.key.day.toString()
            }
        }

    private val _monthlyStatistics: MutableLiveData<Map<SimpleDate, Int>> = UniqueLiveData()
    val monthlyStatistics: LiveData<Map<String, Int>>
        get() = _monthlyStatistics.map { statistics ->
            statistics.mapKeys { entry ->
                entry.key.day.toString()
            }
        }

    init {
        collectReadPagesStatistics()
        changeWeek()
        changeMonth()
    }

    override fun handleEvent(event: StatisticsEvent) {
        when (event) {
            StatisticsEvent.OnPreviousWeekButtonClicked, StatisticsEvent.OnWeeklyStatisticsChartSwipedRight -> {
                weekDate = weekDate.addWeeks(-1)
                changeWeek()
            }

            StatisticsEvent.OnNextWeekButtonClicked, StatisticsEvent.OnWeeklyStatisticsChartSwipedLeft -> {
                val newDate = weekDate.addWeeks(1)
                if (newDate <= currentWeekDate) {
                    weekDate = newDate
                    changeWeek()
                }
            }

            StatisticsEvent.OnPreviousMonthButtonClicked, StatisticsEvent.OnMonthlyStatisticsChartSwipedRight -> {
                monthDate = monthDate.addMonth(-1)
                changeMonth()
            }

            StatisticsEvent.OnNextMonthButtonClicked, StatisticsEvent.OnMonthlyStatisticsChartSwipedLeft -> {
                val newDate = monthDate.addMonth(1)
                if (newDate <= currentMonthDate) {
                    monthDate = newDate
                    changeMonth()
                }
            }
        }
    }

    private fun changeWeek() {
        collectWeeklyStatistics()
        val week = DateHelper.generateWeek(weekDate)
        _weekText.value = getWeekText(week)
    }

    private fun changeMonth() {
        collectMonthlyStatistics()
        _monthText.value = getMonthText()
    }

    private fun collectReadPagesStatistics() {
        viewModelScope.launch {
            observeReadPagesStatistics()
                .collect { _readPagesStatistics.value = it }
        }
    }

    private fun collectWeeklyStatistics() {
        viewModelScope.launch {
            observeWeeklyReadingStatistics(weekDate)
                .collect { _weeklyStatistics.value = it }
        }
    }

    private fun collectMonthlyStatistics() {
        viewModelScope.launch {
            observeMonthlyReadingStatistics(monthDate)
                .collect { _monthlyStatistics.value = it }
        }
    }

    private fun getWeekText(week: Week): String {
        return buildString {
            append(week.startDate.format())
            append(" - ")
            append(week.endDate.format())
        }
    }

    private fun getMonthText(): String {
        return buildString {
            append(monthDate.year)
            append("-")
            if (monthDate.month < 10) {
                append(0)
            }
            append(monthDate.month)
        }
    }
}