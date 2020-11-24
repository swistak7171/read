package pl.kamilszustak.read.ui.main.collection.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.map
import pl.kamilszustak.read.domain.access.usecase.goal.AddDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.GetLatestDailyReadingGoalUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class ReadingGoalViewModel @Inject constructor(
    private val addDailyReadingGoal: AddDailyReadingGoalUseCase,
    private val getLatestDailyReadingGoal: GetLatestDailyReadingGoalUseCase,
) : BaseViewModel<ReadingGoalEvent, ReadingGoalAction>() {

    val isGoalEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
    val switchLabelResourceId: LiveData<Int> = isGoalEnabled.map(R.string.empty_placeholder) { isEnabled ->
        if (isEnabled) {
            R.string.reading_goal_on
        } else {
            R.string.reading_goal_off
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
            val goal = getLatestDailyReadingGoal()
            if (goal != null) {
                goalPagesNumber.value = goal.pagesNumber
                _goalTime.value = goal.reminderTime
            }
        }
    }

    override fun handleEvent(event: ReadingGoalEvent) {
        when (event) {
            ReadingGoalEvent.OnHourEditTextClicked -> handleTimeEditTextClick()
            is ReadingGoalEvent.OnTimeSelected -> handleTimeSelection(event)
            ReadingGoalEvent.OnSaveButtonClicked -> handleSaveButtonClick()
        }
    }

    private fun handleTimeEditTextClick() {
        val currentTime = Time.current()
        _action.value = ReadingGoalAction.ShowTimePicker(R.string.reminder_time, currentTime)
    }

    private fun handleTimeSelection(event: ReadingGoalEvent.OnTimeSelected) {
        _goalTime.value = event.time
    }

    private fun handleSaveButtonClick() {
        val isEnabled = isGoalEnabled.value ?: false
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
                    _action.value = ReadingGoalAction.ReadingGoalSet
                }.onFailure {
                    _action.value = ReadingGoalAction.Error(R.string.reading_goal_setting_error_message)
                }
        }

    }
}