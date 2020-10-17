package pl.kamilszustak.read.ui.main.book.progress

import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingDialogFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.DialogFragmentReadingProgressBinding
import javax.inject.Inject

class ReadingProgressDialogFragment @Inject constructor(
    viewModelFactory: ReadingProgressViewModelFactory,
) : MainDataBindingDialogFragment<DialogFragmentReadingProgressBinding, ReadingProgressViewModel>(R.layout.dialog_fragment_reading_progress) {

    override val viewModel: ReadingProgressViewModel by viewModels(viewModelFactory)

    override fun setListeners() {
        with(binding) {
            saveButton.setOnClickListener {
                viewModel.dispatchEvent(ReadingProgressEvent.OnSaveButtonClicked)
            }

            cancelButton.setOnClickListener {
                viewModel.dispatchEvent(ReadingProgressEvent.OnCancelButtonClicked)
            }
        }
    }

    override fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                ReadingProgressState.NavigateUp -> navigateUp()
            }
        }
    }
}