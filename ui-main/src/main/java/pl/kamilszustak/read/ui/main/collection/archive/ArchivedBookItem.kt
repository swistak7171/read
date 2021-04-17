package pl.kamilszustak.read.ui.main.collection.archive

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.common.date.DateFormats
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.util.load
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ItemArchivedBookBinding

class ArchivedBookItem(book: Book) : ModelAbstractBindingItem<Book, ItemArchivedBookBinding>(book) {
    override var identifier: Long
        get() = model.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_archived_book_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemArchivedBookBinding =
        ItemArchivedBookBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemArchivedBookBinding, payloads: List<Any>) {
        with(binding) {
            model.coverImageUrl?.let { coverImageView.load(it) }
            titleTextView.text = model.title
            model.archivingDate?.let { archivingDate ->
                val date = DateFormats.dateFormat.format(archivingDate)
                archivingDateTextView.text = binding.root.context?.getString(R.string.book_archiving_date, date)
            }
        }
    }

    override fun unbindView(binding: ItemArchivedBookBinding) {
        with(binding) {
            coverImageView.setImageDrawable(null)
            titleTextView.text = null
            archivingDateTextView.text = null
        }
    }
}