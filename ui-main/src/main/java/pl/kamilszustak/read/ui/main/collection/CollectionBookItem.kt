package pl.kamilszustak.read.ui.main.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ItemCollectionBookBinding

class CollectionBookItem(book: CollectionBook) : ModelAbstractBindingItem<CollectionBook, ItemCollectionBookBinding>(book) {
    override var identifier: Long
        get() = model.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_collection_book_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCollectionBookBinding =
        ItemCollectionBookBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemCollectionBookBinding, payloads: List<Any>) {
        binding.book = model
    }

    override fun unbindView(binding: ItemCollectionBookBinding) {
        with(binding) {
            book = null
            executePendingBindings()
        }
    }
}