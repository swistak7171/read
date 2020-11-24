package pl.kamilszustak.read.ui.main.collection.goal

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.successToast
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_reading_goal_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveItem -> {
                viewModel.dispatchEvent(ReadingGoalEvent.OnSaveButtonClicked)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun setListeners() {
        binding.timeEditText.setOnClickListener {
            viewModel.dispatchEvent(ReadingGoalEvent.OnHourEditTextClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ReadingGoalAction.ShowTimePicker -> showTimePicker(action)
                ReadingGoalAction.ReadingGoalSet -> successToast(R.string.reading_goal_set)
                is ReadingGoalAction.Error -> errorToast(action.messageResourceId)
            }
        }
    }

    private fun showTimePicker(action: ReadingGoalAction.ShowTimePicker) {
        val title = getString(action.titleResourceId)
        val timePicker = MaterialTimePicker.Builder()
            .setTitleText(title)
            .setTimeFormat(TimeFormat.CLOCK_24H)
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