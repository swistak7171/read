package pl.kamilszustak.read.model.domain.statistics

import pl.kamilszustak.read.common.date.SimpleDate

data class WeeklyStatistics(
    val startDate: SimpleDate,
    val endDate: SimpleDate,
    val data: Map<String, Int>,
)