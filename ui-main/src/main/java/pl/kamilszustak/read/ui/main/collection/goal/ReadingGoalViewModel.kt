package pl.kamilszustak.read.ui.main.collection.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.map
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class ReadingGoalViewModel @Inject constructor() : BaseViewModel<ReadingGoalEvent, ReadingGoalAction>() {
    val isEnabled: MutableLiveData<Boolean> = UniqueLiveData()
    val switchLabelResourceId: LiveData<Int> = isEnabled.map(R.string.empty_placeholder) { isEnabled ->
        if (isEnabled) {
            R.string.reading_goal_on
        } else {
            R.string.reading_goal_off
        }
    }
}