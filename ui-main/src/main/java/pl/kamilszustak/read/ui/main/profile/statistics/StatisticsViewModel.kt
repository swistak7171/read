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
import pl.kamilszustak.read.domain.access.usecase.statistics.*
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val observeReadPagesStatistics: ObserveReadPagesStatisticsUseCase,
    private val observeReadBooksStatistics: ObserveReadBooksStatisticsUseCase,
    private val observeWeeklyReadingStatistics: ObserveWeeklyReadingStatisticsUseCase,
    private val observeMonthlyReadingStatistics: ObserveMonthlyReadingStatisticsUseCase,
    private val observeYearlyReadingStatistics: ObserveYearlyReadingStatisticsUseCase,
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

    private var year: Int = SimpleDate.current().year
        set(value) {
            field = value
            _isNextYearButtonEnabled.value = (value != currentYear)
        }

    private val currentWeekDate: SimpleDate = SimpleDate.current()
    private val currentMonthDate: SimpleDate = SimpleDate.current()
    private val currentYear: Int = SimpleDate.current().year

    val readPagesText: LiveData<String>
        get() = _readPagesStatistics.map(::getPagesBooksText)

    val readBooksText: LiveData<String>
        get() = _readBooksStatistics.map(::getPagesBooksText)

    private val _weekText: MutableLiveData<String> = UniqueLiveData()
    val weekText: LiveData<String>
        get() = _weekText

    private val _monthText: MutableLiveData<String> = UniqueLiveData()
    val monthText: LiveData<String>
        get() = _monthText

    private val _yearText: MutableLiveData<String> = UniqueLiveData()
    val yearText: LiveData<String>
        get() = _yearText

    private val _isNextWeekButtonEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val isNextWeekButtonEnabled: LiveData<Boolean>
        get() = _isNextWeekButtonEnabled

    private val _isNextMonthButtonEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val isNextMonthButtonEnabled: LiveData<Boolean>
        get() = _isNextMonthButtonEnabled

    private val _isNextYearButtonEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val isNextYearButtonEnabled: LiveData<Boolean>
        get() = _isNextYearButtonEnabled

    private val _readPagesStatistics: MutableLiveData<Pair<Int, Int>> = UniqueLiveData()
    val readPagesStatistics: LiveData<Pair<Float, Float>>
        get() = _readPagesStatistics.map { statistics ->
            statistics.first.toFloat() to statistics.second.toFloat()
        }

    private val _readBooksStatistics: MutableLiveData<Pair<Int, Int>> = UniqueLiveData()
    val readBooksStatistics: LiveData<Pair<Float, Float>>
        get() = _readBooksStatistics.map { statistics ->
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

    private val _yearlyStatistics: MutableLiveData<Map<Int, Int>> = UniqueLiveData()
    val yearlyStatistics: LiveData<Map<String, Int>>
        get() = _yearlyStatistics.map { statistics ->
            statistics.mapKeys { entry ->
                entry.key.toString()
            }
        }

    init {
        collectReadPagesStatistics()
        collectReadBooksStatistics()
        changeWeek()
        changeMonth()
        changeYear()
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

            StatisticsEvent.OnPreviousYearButtonClicked, StatisticsEvent.OnYearlyStatisticsChartSwipedLeft -> {
                year -= 1
                changeYear()
            }

            StatisticsEvent.OnNextYearButtonClicked, StatisticsEvent.OnYearlyStatisticsChartSwipedRight -> {
                val newYear = year + 1
                if (newYear <= currentYear) {
                    year = newYear
                    changeYear()
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

    private fun changeYear() {
        collectYearlyStatistics()
        _yearText.value = year.toString()
    }

    private fun collectReadPagesStatistics() {
        viewModelScope.launch {
            observeReadPagesStatistics()
                .collect { _readPagesStatistics.value = it }
        }
    }

    private fun collectReadBooksStatistics() {
        viewModelScope.launch {
            observeReadBooksStatistics()
                .collect { _readBooksStatistics.value = it }
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

    private fun collectYearlyStatistics() {
        viewModelScope.launch {
            observeYearlyReadingStatistics(year)
                .collect { _yearlyStatistics.value = it }
        }
    }

    private fun getPagesBooksText(statistics: Pair<Int, Int>?): String {
        return if (statistics != null) {
            buildString {
                append(statistics.first)
                append(" / ")
                append(statistics.second)
            }
        } else {
            "0 / 0"
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