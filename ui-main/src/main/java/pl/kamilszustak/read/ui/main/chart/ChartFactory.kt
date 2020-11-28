package pl.kamilszustak.read.ui.main.chart

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper
import com.db.williamchart.view.BarChartView
import pl.kamilszustak.read.ui.main.R

class ChartFactory {
    private val defaultLayoutParams: ViewGroup.LayoutParams
        get() = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

    fun createBarChart(context: Context, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams): BarChartView {
        val contextWrapper = contextThemeWrapperOf(context, R.style.BarChartView)

        return BarChartView(contextWrapper).apply {
            this.layoutParams = layoutParams
        }
    }

    private fun contextThemeWrapperOf(context: Context, @StyleRes styleResourceId: Int): ContextThemeWrapper =
        ContextThemeWrapper(context, styleResourceId)
}