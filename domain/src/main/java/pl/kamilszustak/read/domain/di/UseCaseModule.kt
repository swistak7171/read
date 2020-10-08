package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCaseImpl
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryUseCaseImpl

@Module
interface UseCaseModule {
    @Binds
    fun bindGetAllCountriesUseCase(useCase: GetAllCountriesUseCaseImpl): pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase

    @Binds
    fun bindGetDefaultCountryUseCase(useCase: GetDefaultCountryUseCaseImpl): pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
}