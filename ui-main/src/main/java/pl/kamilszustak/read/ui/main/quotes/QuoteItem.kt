package pl.kamilszustak.read.ui.main.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ItemQuoteBinding

class QuoteItem(quote: Quote) : ModelAbstractBindingItem<Quote, ItemQuoteBinding>(quote) {
    override var identifier: Long
        get() = model.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_quote_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemQuoteBinding =
        ItemQuoteBinding.inflate(inflater, parent, false)

    override fun getViewHolder(viewBinding: ItemQuoteBinding): BindingViewHolder<ItemQuoteBinding> =
        ViewHolder(viewBinding)

    override fun bindView(binding: ItemQuoteBinding, payloads: List<Any>) {
        binding.quote = model
    }

    override fun unbindView(binding: ItemQuoteBinding) {
        with(binding) {
            quote = null
            executePendingBindings()
        }
    }

    class ViewHolder(binding: ItemQuoteBinding) : BindingViewHolder<ItemQuoteBinding>(binding)
}