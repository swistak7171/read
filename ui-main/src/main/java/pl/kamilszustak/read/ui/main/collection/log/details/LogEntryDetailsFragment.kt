package pl.kamilszustak.read.ui.main.collection.log.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.read.common.date.DateFormats
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.load
import pl.kamilszustak.read.ui.base.util.setFormattedText
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.DialogFragmentLogEntryDetailsBinding
import javax.inject.Inject

class LogEntryDetailsFragment @Inject constructor(
    viewModelFactory: LogEntryDetailsViewModelFactory.Factory,
) : BaseFragment<DialogFragmentLogEntryDetailsBinding, LogEntryDetailsViewModel>(R.layout.dialog_fragment_log_entry_details) {

    override val viewModel: LogEntryDetailsViewModel by viewModels { viewModelFactory.create(args) }
    override val binding: DialogFragmentLogEntryDetailsBinding by viewBinding(DialogFragmentLogEntryDetailsBinding::bind)
    private val args: LogEntryDetailsFragmentArgs by navArgs()

    override fun observeViewModel() {
        viewModel.logEntry.observe(viewLifecycleOwner) { entry ->
            with(binding) {
                val url = entry.book.coverImageUrl
                if (url != null) {
                    coverImageView.load(url)
                }

                dateTextView.text = DateFormats.dateTimeFormat.format(entry.creationDate)
                titleTextView.text = entry.book.title
                startPageTextView.setFormattedText(R.string.start_page_number, entry.startPage)
                endPageTextView.setFormattedText(R.string.end_page_number, entry.endPage)
                readPagesTextView.text = entry.readPages.toString()
            }
        }
    }
}