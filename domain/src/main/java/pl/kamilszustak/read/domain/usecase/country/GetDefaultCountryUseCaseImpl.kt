package pl.kamilszustak.read.domain.usecase.country

import android.app.Application
import pl.kamilszustak.read.data.access.repository.CountryRepository
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.model.domain.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDefaultCountryUseCaseImpl @Inject constructor(
    private val application: Application,
    private val countryRepository: CountryRepository,
    private val getDefaultCountryCode: GetDefaultCountryCodeUseCase,
) : GetDefaultCountryUseCase {

    override fun invoke(): Country? {
        val code = getDefaultCountryCode() ?: return null

        return countryRepository.getByCode(code)
    }
}