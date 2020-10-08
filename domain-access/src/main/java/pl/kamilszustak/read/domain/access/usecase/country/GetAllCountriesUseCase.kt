package pl.kamilszustak.read.domain.access.usecase.country

import pl.kamilszustak.read.data.model.Country
import pl.kamilszustak.read.domain.access.usecase.UseCase

interface GetAllCountriesUseCase : pl.kamilszustak.read.domain.access.usecase.UseCase<List<Country>>