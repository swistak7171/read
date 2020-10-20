package pl.kamilszustak.read.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.util.load
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ItemVolumeBinding

class VolumeItem(volume: Volume) : ModelAbstractBindingItem<Volume, ItemVolumeBinding>(volume) {
    override var identifier: Long
        get() = model.id.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_volume_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemVolumeBinding =
        ItemVolumeBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemVolumeBinding, payloads: List<Any>) {
        with(binding) {
            val imageUrl = model.coverImageUrl
            if (imageUrl != null) {
                coverImageView.load(imageUrl)
            }

            titleTextView.text = model.title

            with(authorTextView) {
                text = model.author
                isVisible = !model.author.isNullOrBlank()
            }
        }
    }

    override fun unbindView(binding: ItemVolumeBinding) {
        with(binding) {
            coverImageView.setImageDrawable(null)
            titleTextView.text = null

            with(authorTextView) {
                text = null
                isVisible = true
            }
        }
    }
}