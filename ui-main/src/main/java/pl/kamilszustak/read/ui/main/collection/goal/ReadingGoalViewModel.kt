package pl.kamilszustak.read.ui.main.collection.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.common.date.currentTime
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.map
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class ReadingGoalViewModel @Inject constructor() : BaseViewModel<ReadingGoalEvent, ReadingGoalAction>() {
    val isGoalEnabled: MutableLiveData<Boolean> = UniqueLiveData()
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
            if (time != null) {
                "${time.hour}:${time.minute}"
            } else {
                ""
            }
        }

    override fun handleEvent(event: ReadingGoalEvent) {
        when (event) {
            ReadingGoalEvent.OnHourEditTextClicked -> handleTimeEditTextClick()
            is ReadingGoalEvent.OnTimeSelected -> handleTimeSelection(event)
        }
    }

    private fun handleTimeEditTextClick() {
        val time = currentTime()
        _action.value = ReadingGoalAction.ShowTimePicker(R.string.reminder_time, time)
    }

    private fun handleTimeSelection(event: ReadingGoalEvent.OnTimeSelected) {
        _goalTime.value = event.time
    }
}