package pl.kamilszustak.read.ui.main.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ItemBookBinding

class BookItem(book: Book) : ModelAbstractBindingItem<Book, ItemBookBinding>(book) {
    override var identifier: Long
        get() = model.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_collection_book_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemBookBinding =
        ItemBookBinding.inflate(inflater, parent, false)

    override fun getViewHolder(viewBinding: ItemBookBinding): BindingViewHolder<ItemBookBinding> =
        ViewHolder(viewBinding)

    override fun bindView(binding: ItemBookBinding, payloads: List<Any>) {
        binding.book = model
    }

    override fun unbindView(binding: ItemBookBinding) {
        with(binding) {
            book = null
            executePendingBindings()
        }
    }

    class ViewHolder(binding: ItemBookBinding) : BindingViewHolder<ItemBookBinding>(binding)
}