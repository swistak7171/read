package pl.kamilszustak.read.ui.main.collection.log

import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.ui.base.binding.viewBinding
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
    private val modelAdapter: ModelAdapter<LogEntry, LogEntryItem> by lazy {
        ModelAdapter { LogEntryItem(it) }
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter)
        binding.readingLogRecyclerView.apply {
            adapter = fastAdapter
        }
    }

    override fun observeViewModel() {
        viewModel.readingLog.observe(viewLifecycleOwner) { entries ->
            modelAdapter.updateModels(entries)
        }
    }
}