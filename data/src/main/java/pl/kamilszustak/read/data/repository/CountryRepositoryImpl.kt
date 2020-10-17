package pl.kamilszustak.read.data.repository

import android.app.Application
import android.content.res.Resources
import androidx.core.content.ContextCompat
import pl.kamilszustak.read.common.util.tryOrNull
import pl.kamilszustak.read.data.R
import pl.kamilszustak.read.data.access.repository.CountryRepository
import pl.kamilszustak.read.model.domain.Country
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val application: Application
) : CountryRepository {

    @OptIn(ExperimentalStdlibApi::class)
    override fun getAll(): List<Country> {
        return with(application.resources) {
            val names = getStringArray(R.array.country_names_by_code)
            val codes = getStringArray(R.array.country_codes_a_z)
            val longCodes = getStringArray(R.array.country_codes_3_a_z)
            val extensions = getStringArray(R.array.country_extensions_by_country_code)

            if (
                names.isNullOrEmpty() ||
                codes.isNullOrEmpty() ||
                longCodes.isNullOrEmpty() ||
                extensions.isNullOrEmpty()
            ) {
                throw Resources.NotFoundException("Countries resources not found")
            }

            buildList {
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
                    val identifier = getIdentifier("flag_$lowerCaseCode", "drawable", application.packageName)
                    val flagDrawable = tryOrNull {
                        application.getDrawable(identifier)
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
    }

    override fun getByCode(code: String): Country? {
        return with(application.resources) {
            val names = getStringArray(R.array.country_names_by_code)
            val codes = getStringArray(R.array.country_codes_a_z)
            val longCodes = getStringArray(R.array.country_codes_3_a_z)
            val extensions = getStringArray(R.array.country_extensions_by_country_code)

            if (
                names.isNullOrEmpty() ||
                codes.isNullOrEmpty() ||
                longCodes.isNullOrEmpty() ||
                extensions.isNullOrEmpty()
            ) {
                throw Resources.NotFoundException("Countries resources not found")
            }

            codes.forEachIndexed { index, code ->
                if (code.toLowerCase(Locale.getDefault()) != code.toLowerCase(Locale.getDefault())) {
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
                val identifier = getIdentifier("flag_$lowerCaseCode", "drawable", application.packageName)
                val flagDrawable = tryOrNull {
                    application.getDrawable(identifier)
                }

                return Country(
                    name = name,
                    code = longCode,
                    extension = extension,
                    flagDrawable = flagDrawable
                )
            }

            return null
        }
    }
}