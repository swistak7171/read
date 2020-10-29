package pl.kamilszustak.read.ui.main.book.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.ObserveBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel

class BookDetailsViewModel(
    private val arguments: BookDetailsFragmentArgs,
    private val observeBook: ObserveBookUseCase,
    private val deleteBook: DeleteBookUseCase,
) : BaseViewModel<BookDetailsEvent, BookDetailsAction>() {

    val book: LiveData<Book> = observeBook(BookId(arguments.bookId))
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: BookDetailsEvent) {
    }
}