package pl.kamilszustak.read.ui.main.book.edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.base.util.successToast
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentBookEditBinding
import java.util.*
import javax.inject.Inject

class BookEditFragment @Inject constructor(
    viewModelFactory: BookEditViewModelFactory.Factory,
) : MainDataBindingFragment<FragmentBookEditBinding, BookEditViewModel>(R.layout.fragment_book_edit) {

    override val viewModel: BookEditViewModel by viewModels { viewModelFactory.create(args) }
    private val args: BookEditFragmentArgs by navArgs()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_book_edit_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveBookItem -> {
                viewModel.dispatchEvent(BookEditEvent.OnSaveBookButtonClicked)
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
        binding.publicationDateEditText.setOnClickListener {
            viewModel.dispatchEvent(BookEditEvent.OnDateEditTextClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.actionBarTitle.observe(viewLifecycleOwner) { titleResourceId ->
            titleResourceId.useOrNull { resourceId ->
                (activity as? AppCompatActivity)?.supportActionBar?.title = getString(resourceId)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                BookEditState.OpenDatePicker -> {
                    openDatePicker()
                }

                is BookEditState.Error -> {
                    errorToast(state.messageResourceId)
                }

                is BookEditState.BookSaved -> {
                    successToast(state.messageResourceId)
                }

                BookEditState.NavigateUp -> {
                    navigateUp()
                }
            }
        }
    }

    private fun openDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(System.currentTimeMillis())
            .setTitleText(R.string.select_a_publication_date)
            .build()

        datePicker.addOnPositiveButtonClickListener { timestamp ->
            if (timestamp != null) {
                val date = Date(timestamp)
                val event = BookEditEvent.OnPublicationDateSelected(date)
                viewModel.dispatchEvent(event)
            }
        }

        datePicker.show(childFragmentManager, DATE_PICKER_TAG)
    }

    companion object {
        const val DATE_PICKER_TAG: String = "publication_date_picker"
    }
}