package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.domain.Country

interface CountryRepository {
    fun getAll(): List<Country>
    fun getByCode(countryCode: String): Country?
}