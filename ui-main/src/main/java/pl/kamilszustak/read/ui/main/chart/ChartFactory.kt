package pl.kamilszustak.read.ui.main.chart

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import com.db.williamchart.view.BarChartView
import pl.kamilszustak.read.ui.main.R

class ChartFactory(
    private val context: Context,
) {
    fun createBarChart(data: List<Pair<String, Float>>): BarChartView {
        val contextWrapper = ContextThemeWrapper(context, R.style.BarChartView)

        return BarChartView(contextWrapper).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            show(data)
        }
    }
}