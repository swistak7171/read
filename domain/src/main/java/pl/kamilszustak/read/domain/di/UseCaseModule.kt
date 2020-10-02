package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCaseImpl

@Module
interface UseCaseModule {
    @Binds
    fun bindGetAllCountriesUseCase(useCase: GetAllCountriesUseCaseImpl): GetAllCountriesUseCase
}