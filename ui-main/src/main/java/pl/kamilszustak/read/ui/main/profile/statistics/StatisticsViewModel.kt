package pl.kamilszustak.read.ui.main.profile.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class StatisticsViewModel @Inject constructor() : BaseViewModel<StatisticsEvent, StatisticsAction>() {
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

    private val _weeklyStatistics: MutableLiveData<List<Map<String, Int>>> = UniqueLiveData()
    val weeklyStatistics: LiveData<List<Map<String, Int>>>
        get() = _weeklyStatistics

    private val _monthlyStatistics: MutableLiveData<List<Map<String, Int>>> = UniqueLiveData()
    val monthlyStatistics: LiveData<List<Map<String, Int>>>
        get() = _monthlyStatistics
}