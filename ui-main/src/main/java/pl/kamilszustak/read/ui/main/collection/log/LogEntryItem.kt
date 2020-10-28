package pl.kamilszustak.read.ui.main.collection.log

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.ui.base.util.load
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ItemLogEntryBinding

class LogEntryItem(logEntry: LogEntry) : ModelAbstractBindingItem<LogEntry, ItemLogEntryBinding>(logEntry) {
    override var identifier: Long
        get() = model.id.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_log_entry_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemLogEntryBinding =
        ItemLogEntryBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemLogEntryBinding, payloads: List<Any>) {
        with(binding) {
            with(coverImageView) {
                val url = model.book.coverImageUrl
                if (url != null) {
                    load(url)
                }
            }

            titleTextView.text = model.book.title
            contentTextView.text = root.resources.getQuantityString(
                R.plurals.reading_log_entry_content,
                model.readPages,
                model.readPages
            )
        }
    }

    override fun unbindView(binding: ItemLogEntryBinding) {
        with(binding) {
            titleTextView.text = null
            contentTextView.text = null
        }
    }
}