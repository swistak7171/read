package pl.kamilszustak.read.domain.usecase.country

import pl.kamilszustak.read.data.model.Country
import pl.kamilszustak.read.data.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCountriesUseCaseImpl @Inject constructor(
    private val countryRepository: CountryRepository
) : GetAllCountriesUseCase {

    override fun invoke(): List<Country> =
        countryRepository.getAll()
}