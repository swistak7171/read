package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentBookEditBinding
import javax.inject.Inject

class BookEditFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : MainDataBindingFragment<FragmentBookEditBinding, BookEditViewModel>(R.layout.fragment_book_edit) {

    override val viewModel: BookEditViewModel by viewModels(viewModelFactory)
}