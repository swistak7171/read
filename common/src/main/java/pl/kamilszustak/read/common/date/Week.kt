package pl.kamilszustak.read.common.date

data class Week(
    val startDate: SimpleDate,
) {
    val endDate: SimpleDate
        get() = startDate.addDays(6)

    @OptIn(ExperimentalStdlibApi::class)
    val days: List<SimpleDate>
        get() = buildList {
            add(startDate)
            for (i in 1..6) {
                val day = startDate.addDays(i)
                add(day)
            }
        }
}
