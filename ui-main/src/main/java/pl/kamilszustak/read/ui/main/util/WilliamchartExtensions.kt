package pl.kamilszustak.read.ui.main.util

import com.db.williamchart.view.AxisChartView

fun AxisChartView.show(map: Map<String, Number>) {
    val list = map.toList()
        .map { it.first to it.second.toFloat() }

    show(list)
}