package pl.kamilszustak.read.domain.usecase.country

import android.app.Application
import android.os.Build
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDefaultCountryCodeUseCaseImpl @Inject constructor(
    private val application: Application
) : GetDefaultCountryCodeUseCase {

    override fun invoke(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            application.resources.configuration.locales.get(0).country
        } else {
            application.resources.configuration.locale.country
        }
    }
}