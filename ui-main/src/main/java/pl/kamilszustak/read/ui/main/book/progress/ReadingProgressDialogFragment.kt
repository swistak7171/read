package pl.kamilszustak.read.ui.main.book.progress

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.main.MainDataBindingDialogFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.DialogFragmentReadingProgressBinding
import javax.inject.Inject

class ReadingProgressDialogFragment @Inject constructor(
    viewModelFactory: ReadingProgressViewModelFactory.Factory,
) : MainDataBindingDialogFragment<DialogFragmentReadingProgressBinding, ReadingProgressViewModel>(R.layout.dialog_fragment_reading_progress) {

    override val viewModel: ReadingProgressViewModel by viewModels { viewModelFactory.create(args)}
    private val args: ReadingProgressDialogFragmentArgs by navArgs()

    override fun setListeners() {
        binding.saveButton.setOnClickListener {
            viewModel.dispatchEvent(ReadingProgressEvent.OnSaveButtonClicked)
        }

        binding.cancelButton.setOnClickListener {
            viewModel.dispatchEvent(ReadingProgressEvent.OnCancelButtonClicked)
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