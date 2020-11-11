package pl.kamilszustak.read.ui.main.collection.log

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.navigate
import pl.kamilszustak.read.ui.base.util.updateModels
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentReadingLogBinding
import javax.inject.Inject

class ReadingLogFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentReadingLogBinding, ReadingLogViewModel>(R.layout.fragment_reading_log) {

    override val viewModel: ReadingLogViewModel by viewModels(viewModelFactory)
    override val binding: FragmentReadingLogBinding by viewBinding(FragmentReadingLogBinding::bind)
    private val navigator: Navigator = Navigator()
    private val modelAdapter: ModelAdapter<LogEntry, LogEntryItem> by lazy {
        ModelAdapter { LogEntryItem(it) }
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            onClickListener = { view, adapter, item, position ->
                val event = ReadingLogEvent.OnLogEntryClicked(item.model.id)
                viewModel.dispatchEvent(event)
                true
            }
        }

        binding.readingLogRecyclerView.apply {
            adapter = fastAdapter
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ReadingLogAction.NavigateToLogEntryDetailsFragment -> {
                    navigator.navigateToLogEntryDetailsDialogFragment(action.logEntryId)
                }
            }
        }

        viewModel.readingLog.observe(viewLifecycleOwner) { entries ->
            binding.readingLogRecyclerView.isVisible = entries.isNotEmpty()
            binding.emptyReadingLogView.root.isVisible = entries.isEmpty()
            modelAdapter.updateModels(entries)
        }
    }

    private inner class Navigator {
        fun navigateToLogEntryDetailsDialogFragment(logEntryId: LogEntryId) {
            val direction = ReadingLogFragmentDirections.actionReadingLogFragmentToLogEntryDetailsFragment(logEntryId.value)
            navigate(direction)
        }
    }
}