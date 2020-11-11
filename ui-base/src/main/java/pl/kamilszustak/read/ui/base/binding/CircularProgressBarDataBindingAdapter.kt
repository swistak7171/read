package pl.kamilszustak.read.ui.base.binding

import androidx.databinding.BindingAdapter
import com.mikhaellopez.circularprogressbar.CircularProgressBar

object CircularProgressBarDataBindingAdapter {
    private const val CPB_PROGRESS_MAX_ATTRIBUTE: String = "cpb_progress_max"
    private const val CPB_PROGRESS_ATTRIBUTE: String = "cpb_progress"

    @BindingAdapter(CPB_PROGRESS_MAX_ATTRIBUTE)
    @JvmStatic
    fun CircularProgressBar.setMaxProgress(value: Number?) {
        if (value != null) {
            progressMax = value.toFloat()
        }
    }

    @BindingAdapter(CPB_PROGRESS_ATTRIBUTE)
    @JvmStatic
    fun CircularProgressBar.setProgress(value: Number?) {
        if (value != null) {
            progress = value.toFloat()
        }
    }
}