package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase

interface GenerateMonthlyReadingStatistcsUseCase : BaseUseCase {
    suspend operator fun invoke(date: SimpleDate): Map<String, Int>?
}