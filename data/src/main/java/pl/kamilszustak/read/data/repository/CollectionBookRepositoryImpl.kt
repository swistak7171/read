package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.data.qualifier.CollectionBookReference
import pl.kamilszustak.read.model.domain.CollectionBook
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionBookRepositoryImpl @Inject constructor(
    @CollectionBookReference private val reference: DatabaseReference,
) : CollectionBookRepository {

    override fun add(book: CollectionBook) {
        with(reference) {
            push().key.useOrNull { book.id = it }
            setValue(book)
        }
    }
}