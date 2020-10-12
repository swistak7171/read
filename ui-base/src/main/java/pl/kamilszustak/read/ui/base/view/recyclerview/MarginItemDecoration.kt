package pl.kamilszustak.read.ui.base.view.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.kamilszustak.read.ui.base.R

class MarginItemDecoration(
    private val isHorizontalMarginEnabled: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spaceHeight = view.resources.getDimension(R.dimen.default_margin).toInt()
        outRect.apply {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            bottom = spaceHeight

            if (isHorizontalMarginEnabled) {
                left = spaceHeight
                right = spaceHeight
            }
        }
    }
}