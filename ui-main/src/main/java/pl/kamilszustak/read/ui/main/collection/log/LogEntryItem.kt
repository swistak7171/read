package pl.kamilszustak.read.ui.main.collection.log

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.model.domain.LogEntry
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
    }

    override fun unbindView(binding: ItemLogEntryBinding) {

    }
}