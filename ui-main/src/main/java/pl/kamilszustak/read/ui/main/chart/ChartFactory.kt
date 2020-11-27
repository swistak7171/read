package pl.kamilszustak.read.ui.main.chart

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper
import com.db.williamchart.view.BarChartView
import pl.kamilszustak.read.ui.main.R

class ChartFactory(
    private val context: Context,
) {
    private val defaultLayoutParams: ViewGroup.LayoutParams
        get() = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

    fun createBarChart(data: List<Pair<String, Float>>, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams): BarChartView {
        return BarChartView(contextThemeWrapperOf(R.style.BarChartView)).apply {
            this.layoutParams = layoutParams

            show(data)
        }
    }

    private fun contextThemeWrapperOf(@StyleRes styleResourceId: Int): ContextThemeWrapper =
        ContextThemeWrapper(context, styleResourceId)
}