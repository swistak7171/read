package pl.kamilszustak.read.ui.main.book.edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.base.util.successToast
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentBookEditBinding
import javax.inject.Inject

class BookEditFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : MainDataBindingFragment<FragmentBookEditBinding, BookEditViewModel>(R.layout.fragment_book_edit) {

    override val viewModel: BookEditViewModel by viewModels(viewModelFactory)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_book_edit_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addBookItem -> {
                viewModel.dispatchEvent(BookEditEvent.OnAddBookButtonClicked)
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

    override fun observeViewModel() {
        viewModel.actionBarTitle.observe(viewLifecycleOwner) { titleResourceId ->
            titleResourceId.useOrNull { resourceId ->
                (activity as? AppCompatActivity)?.supportActionBar?.title = getString(resourceId)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is BookEditState.Error -> {
                    errorToast(state.messageResourceId)
                }

                BookEditState.BookAdded -> {
                    successToast(R.string.book_added_successfully)
                    navigateUp()
                }
            }
        }
    }
}