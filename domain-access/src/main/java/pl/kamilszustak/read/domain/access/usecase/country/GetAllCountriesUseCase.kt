package pl.kamilszustak.read.domain.access.usecase.country

import pl.kamilszustak.read.domain.access.usecase.UseCase
import pl.kamilszustak.read.model.domain.Country

interface GetAllCountriesUseCase : UseCase<List<Country>>