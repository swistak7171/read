package pl.kamilszustak.read.ui.main.util

import com.db.williamchart.view.AxisChartView

private fun mapEntries(entries: Map<String, Number>): List<Pair<String, Float>> {
    return entries.toList()
        .map { it.first to it.second.toFloat() }
}

fun AxisChartView.show(map: Map<String, Number>) {
    val entries = mapEntries(map)
    show(entries)
}

fun AxisChartView.animate(map: Map<String, Number>) {
    val entries = mapEntries(map)
    animate(entries)
}