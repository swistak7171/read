package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.domain.access.DateFormats
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

class BookEditViewModel @Inject constructor(
    private val dateFormats: DateFormats,
) : BaseViewModel<BookEditEvent, BookEditState>() {

    private val _actionBarTitle: UniqueLiveData<Int> = UniqueLiveData()
    val actionBarTitle: LiveData<Int>
        get() = _actionBarTitle

    val bookTitle: UniqueLiveData<String> = UniqueLiveData()
    val bookAuthor: UniqueLiveData<String> = UniqueLiveData()
    val numberOfPages: UniqueLiveData<Int> = UniqueLiveData()
    val bookIsbn: UniqueLiveData<String?> = UniqueLiveData()
    val bookDescription: UniqueLiveData<String?> = UniqueLiveData()

    private val _bookPublicationDate: UniqueLiveData<Date?> = UniqueLiveData()
    val bookPublicationDate: LiveData<String?>
        get() = _bookPublicationDate.map { date ->
            date.useOrNull { dateFormats.dateFormat.format(it)  }
        }

    init {
        _actionBarTitle.value = R.string.add_book
    }

    override fun handleEvent(event: BookEditEvent) {
        when (event) {
            BookEditEvent.OnAddBookButtonClicked -> {
                Timber.i("Add book")
            }
        }
    }
}