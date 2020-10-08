package pl.kamilszustak.read.domain.usecase.country

import android.app.Application
import android.os.Build
import pl.kamilszustak.read.data.model.Country
import pl.kamilszustak.read.data.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDefaultCountryUseCaseImpl @Inject constructor(
    private val application: Application,
    private val countryRepository: CountryRepository
) : GetDefaultCountryUseCase {

    override fun invoke(): Country? {
        val code = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            application.resources.configuration.locales.get(0).country
        } else {
            application.resources.configuration.locale.country
        } ?: return null

        return countryRepository.getByCode(code)
    }
}