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
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveWeeklyReadingStatisticsUseCase
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val observeWeeklyReadingStatistics: ObserveWeeklyReadingStatisticsUseCase,
) : BaseViewModel<StatisticsEvent, StatisticsAction>() {

    private var currentDay: SimpleDate = SimpleDate.current()

    private val _weekText: MutableLiveData<String> = UniqueLiveData()
    val weekText: LiveData<String>
        get() = _weekText

    private val _monthText: MutableLiveData<String> = UniqueLiveData()
    val monthText: LiveData<String>
        get() = _monthText

    private val _currentWeekChart: MutableLiveData<Int> = UniqueLiveData()
    val currentWeekChart: LiveData<Int>
        get() = _currentWeekChart

    private val _currentMonthChart: MutableLiveData<Int> = UniqueLiveData()
    val currentMonthChart: LiveData<Int>
        get() = _currentMonthChart

    val _weeklyStatistics: MutableLiveData<Map<SimpleDate, Int>> = UniqueLiveData()
    val weeklyStatistics: LiveData<Map<String, Int>>
        get() = _weeklyStatistics.map { statistics ->
            statistics.mapKeys { entry ->
                entry.key.day.toString()
            }
        }

    val _monthlyStatistics: MutableLiveData<Map<SimpleDate, Int>> = UniqueLiveData()
    val monthlyStatistics: LiveData<Map<String, Int>>
        get() = _monthlyStatistics.map { statistics ->
            statistics.mapKeys { entry ->
                entry.key.day.toString()
            }
        }

    init {
        collectWeeklyStatistics()
        val currentWeek = DateHelper.generateWeek(currentDay)
        _weekText.value = getWeekText(currentWeek)
    }

    override fun handleEvent(event: StatisticsEvent) {
        when (event) {
            StatisticsEvent.OnPreviousWeekButtonClicked -> {}
            StatisticsEvent.OnNextWeekButtonClicked -> {}
            StatisticsEvent.OnPreviousMonthButtonClicked -> {}
            StatisticsEvent.OnNextMonthButtonClicked -> {}
        }
    }

    private fun collectWeeklyStatistics() {
        viewModelScope.launch {
            observeWeeklyReadingStatistics(currentDay)
                .collect { _weeklyStatistics.value = it; Timber.i(it.toString())}
        }
    }

    private fun getWeekText(week: Week): String {
        return buildString {
            append(week.startDate.format())
            append(" - ")
            append(week.endDate.format())
        }
    }
}