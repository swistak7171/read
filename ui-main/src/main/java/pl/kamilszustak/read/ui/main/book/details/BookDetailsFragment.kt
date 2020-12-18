package pl.kamilszustak.read.ui.main.book.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentBookDetailsBinding
import javax.inject.Inject

class BookDetailsFragment @Inject constructor(
    private val viewModelFactory: BookDetailsViewModelFactory.Factory
) : MainDataBindingFragment<FragmentBookDetailsBinding, BookDetailsViewModel>(R.layout.fragment_book_details) {

    override val viewModel: BookDetailsViewModel by viewModels { viewModelFactory.create(args) }
    private val args: BookDetailsFragmentArgs by navArgs()
    private val navigator: Navigator = Navigator()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_book_details_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.updateReadingProgressItem -> {
                viewModel.dispatchEvent(BookDetailsEvent.OnUpdateReadingProgressButtonClicked)
                true
            }

            R.id.editBookItem -> {
                viewModel.dispatchEvent(BookDetailsEvent.OnEditBookButtonClicked)
                true
            }

            R.id.deleteBookItem -> {
                dialog {
                    title(R.string.delete_book_dialog_title)
                    message(R.string.delete_book_dialog_message)
                    positiveButton(R.string.yes) {
                        viewModel.dispatchEvent(BookDetailsEvent.OnDeleteBookButtonClicked)
                    }
                    negativeButton(R.string.no) { dialog ->
                        dialog.dismiss()
                    }
                }

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
        binding.progressBar.setOnClickListener {
            viewModel.dispatchEvent(BookDetailsEvent.OnProgressBarClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is BookDetailsAction.NavigateToBookEditFragment -> {
                    navigator.navigateToBookEditFragment(action.bookId)
                }

                is BookDetailsAction.NavigateToReadingProgressDialogFragment -> {
                    navigator.navigateToReadingProgressDialogFragment(action.bookId)
                }

                BookDetailsAction.BookDeleted -> {
                    successToast(R.string.book_deleted)
                }

                is BookDetailsAction.Error -> {
                    errorToast(action.messageResourceId)
                }

                BookDetailsAction.NavigateUp -> {
                    navigateUp()
                }
            }
        }
    }

    private inner class Navigator {
        fun navigateToBookEditFragment(bookId: BookId) {
            val direction = BookDetailsFragmentDirections.actionBookDetailsFragmentToNavigationBookEdit(bookId.value)
            navigate(direction)
        }

        fun navigateToReadingProgressDialogFragment(bookId: BookId) {
            val direction = BookDetailsFragmentDirections.actionBookDetailsFragmentToReadingProgressDialogFragment(bookId.value)
            navigate(direction)
        }
    }
}