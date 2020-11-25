package pl.kamilszustak.read.ui.main.collection.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.map
import pl.kamilszustak.read.domain.access.storage.SettingsStorage
import pl.kamilszustak.read.domain.access.usecase.goal.AddDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.CancelDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.GetLatestDailyReadingGoalUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class ReadingGoalViewModel @Inject constructor(
    private val settingsStorage: SettingsStorage,
    private val addDailyReadingGoal: AddDailyReadingGoalUseCase,
    private val cancelDailyReadingGoal: CancelDailyReadingGoalUseCase,
    private val getLatestDailyReadingGoal: GetLatestDailyReadingGoalUseCase,
) : BaseViewModel<ReadingGoalEvent, ReadingGoalAction>() {

    val isGoalEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val switchLabelResourceId: LiveData<Int> = isGoalEnabled.map(R.string.empty_placeholder) { isEnabled ->
        if (isEnabled) {
            R.string.daily_reading_goal_on
        } else {
            R.string.daily_reading_goal_off
        }
    }

    val goalPagesNumber: MutableLiveData<Int> = UniqueLiveData()
    private val _goalTime: MutableLiveData<Time> = UniqueLiveData()
    val goalTime: LiveData<String>
        get() = _goalTime.map { time ->
            time?.format() ?: ""
        }

    init {
        viewModelScope.launch {
            launch {
                val goal = getLatestDailyReadingGoal()
                if (goal != null) {
                    goalPagesNumber.value = goal.pagesNumber
                    _goalTime.value = goal.reminderTime
                }
            }

            launch {
                val value = settingsStorage.isDailyReadingGoalEnabled.get()
                if (value != null) {
                    isGoalEnabled.value = value
                }
            }
        }
    }

    override fun handleEvent(event: ReadingGoalEvent) {
        when (event) {
            ReadingGoalEvent.OnTimeEditTextClicked -> handleTimeEditTextClick()
            ReadingGoalEvent.OnTimeClearButtonClicked -> handleTimeClearButtonClick()
            is ReadingGoalEvent.OnTimeSelected -> handleTimeSelection(event)
            ReadingGoalEvent.OnSaveButtonClicked -> handleSaveButtonClick()
        }
    }

    private fun handleTimeEditTextClick() {
        val currentTime = Time.current()
        _action.value = ReadingGoalAction.ShowTimePicker(R.string.reminder_time, currentTime)
    }

    private fun handleTimeClearButtonClick() {
        _goalTime.value = null
    }

    private fun handleTimeSelection(event: ReadingGoalEvent.OnTimeSelected) {
        _goalTime.value = event.time
    }

    private fun handleSaveButtonClick() {
        val isEnabled = isGoalEnabled.value ?: false
        if (!isEnabled) {
            viewModelScope.launch {
                cancelDailyReadingGoal()
                settingsStorage.isDailyReadingGoalEnabled.edit(false)
                _action.value = ReadingGoalAction.ReadingGoalCancelled
            }

            return
        }

        val time = _goalTime.value
        val pagesNumber = goalPagesNumber.value

        if (time == null) {
            _action.value = ReadingGoalAction.Error(R.string.reminder_time_not_selected_error_message)
            return
        }

        if (pagesNumber == null) {
            _action.value = ReadingGoalAction.Error(R.string.number_of_pages_not_entered_error_message)
            return
        }

        viewModelScope.launch {
            val goal = ReadingGoal(
                pagesNumber = pagesNumber,
                reminderTime = time
            )

            addDailyReadingGoal(goal)
                .onSuccess {
                    settingsStorage.isDailyReadingGoalEnabled.edit(true)
                    _action.value = ReadingGoalAction.ReadingGoalSet
                }.onFailure {
                    _action.value = ReadingGoalAction.Error(R.string.daily_reading_goal_setting_error_message)
                }
        }

    }
}