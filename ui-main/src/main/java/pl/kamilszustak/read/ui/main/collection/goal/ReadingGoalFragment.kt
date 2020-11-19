package pl.kamilszustak.read.ui.main.collection.goal

import androidx.lifecycle.ViewModelProvider
import com.google.android.material.timepicker.MaterialTimePicker
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentReadingGoalBinding
import javax.inject.Inject

class ReadingGoalFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentReadingGoalBinding, ReadingGoalViewModel>(R.layout.fragment_reading_goal) {

    override val viewModel: ReadingGoalViewModel by viewModels(viewModelFactory)
    private val TIME_PICKER_TAG: String = "time_picker"

    override fun setListeners() {
        binding.timeEditText.setOnClickListener {
            viewModel.dispatchEvent(ReadingGoalEvent.OnHourEditTextClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ReadingGoalAction.ShowTimePicker -> showTimePicker(action)
            }
        }
    }

    private fun showTimePicker(action: ReadingGoalAction.ShowTimePicker) {
        val title = getString(action.titleResourceId)
        val timePicker = MaterialTimePicker.Builder()
            .setTitleText(title)
            .setHour(action.time.hour)
            .setMinute(action.time.minute)
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    val time = Time(hour, minute)
                    val event = ReadingGoalEvent.OnTimeSelected(time)
                    viewModel.dispatchEvent(event)
                }
            }

        timePicker.show(childFragmentManager, TIME_PICKER_TAG)
    }
}