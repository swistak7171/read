package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.domain.access.usecase.BaseUseCase

interface GenerateMonthlyReadingStatistcsUseCase : BaseUseCase {
    suspend operator fun invoke(year: Int, month: Int): Map<String, Int>?
}