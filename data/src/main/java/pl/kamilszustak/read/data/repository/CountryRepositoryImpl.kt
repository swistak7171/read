package pl.kamilszustak.read.data.repository

import android.content.res.Resources
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.common.util.tryOrNull
import pl.kamilszustak.read.data.R
import pl.kamilszustak.read.data.access.repository.CountryRepository
import pl.kamilszustak.read.model.domain.Country
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
) : CountryRepository {

    @OptIn(ExperimentalStdlibApi::class)
    override fun getAll(): List<Country> {
        val names = resourceProvider.getStringArray(R.array.country_names_by_code)
        val codes = resourceProvider.getStringArray(R.array.country_codes_a_z)
        val longCodes = resourceProvider.getStringArray(R.array.country_codes_3_a_z)
        val extensions = resourceProvider.getStringArray(R.array.country_extensions_by_country_code)

        if (
            names.isNullOrEmpty() ||
            codes.isNullOrEmpty() ||
            longCodes.isNullOrEmpty() ||
            extensions.isNullOrEmpty()
        ) {
            throw Resources.NotFoundException("Countries resources not found")
        }

        return buildList {
            names.forEachIndexed { index, name ->
                val code = codes.getOrNull(index)
                val longCode = longCodes.getOrNull(index)
                val extension = extensions.getOrNull(index)

                if (
                    name.isNullOrBlank() ||
                    code.isNullOrBlank() ||
                    longCode.isNullOrBlank() ||
                    extension.isNullOrBlank()
                ) {
                    throw Resources.NotFoundException("Countries resources are not consistent")
                }

                val lowerCaseCode = code.toLowerCase(Locale.getDefault())
                val identifier = resourceProvider.getId("flag_$lowerCaseCode", "drawable")
                val flagDrawable = tryOrNull {
                    resourceProvider.getDrawable(identifier)
                }

                val country = Country(
                    name = name,
                    code = longCode,
                    extension = extension,
                    flagDrawable = flagDrawable
                )

                add(country)
            }
        }
    }

    override fun getByCode(countryCode: String): Country? {
        val names = resourceProvider.getStringArray(R.array.country_names_by_code)
        val codes = resourceProvider.getStringArray(R.array.country_codes_a_z)
        val longCodes = resourceProvider.getStringArray(R.array.country_codes_3_a_z)
        val extensions = resourceProvider.getStringArray(R.array.country_extensions_by_country_code)

        if (
            names.isNullOrEmpty() ||
            codes.isNullOrEmpty() ||
            longCodes.isNullOrEmpty() ||
            extensions.isNullOrEmpty()
        ) {
            throw Resources.NotFoundException("Countries resources not found")
        }

        var country: Country? = null
        codes.forEachIndexed { index, code ->
            if (code.toLowerCase(Locale.getDefault()) != countryCode.toLowerCase(Locale.getDefault())) {
                return@forEachIndexed
            }

            val name = names.getOrNull(index)
            val longCode = longCodes.getOrNull(index)
            val extension = extensions.getOrNull(index)

            if (
                name.isNullOrBlank() ||
                code.isNullOrBlank() ||
                longCode.isNullOrBlank() ||
                extension.isNullOrBlank()
            ) {
                throw Resources.NotFoundException("Countries resources are not consistent")
            }

            val lowerCaseCode = code.toLowerCase(Locale.getDefault())
            val identifier = resourceProvider.getId("flag_$lowerCaseCode", "drawable")
            val flagDrawable = tryOrNull {
                resourceProvider.getDrawable(identifier)
            }

            country = Country(
                name = name,
                code = longCode,
                extension = extension,
                flagDrawable = flagDrawable
            )
        }

        return country
    }
}