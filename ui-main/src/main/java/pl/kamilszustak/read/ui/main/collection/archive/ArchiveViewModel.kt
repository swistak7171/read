package pl.kamilszustak.read.ui.main.collection.archive

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.domain.access.usecase.book.ObserveAllBooksUseCase
import pl.kamilszustak.read.domain.access.usecase.book.UnarchiveBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class ArchiveViewModel @Inject constructor(
    private val observeAllBooksUseCase: ObserveAllBooksUseCase,
    private val unarchiveBook: UnarchiveBookUseCase,
) : BaseViewModel<ArchiveEvent, ArchiveAction>() {

    val books: LiveData<List<Book>> = observeAllBooksUseCase()
        .map { books ->
            books.filter { it.isArchived }
                .sortedByDescending(Book::archivingDate)
        }
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: ArchiveEvent) {

    }
}