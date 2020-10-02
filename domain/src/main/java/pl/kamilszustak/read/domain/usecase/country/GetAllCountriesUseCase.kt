package pl.kamilszustak.read.domain.usecase.country

import pl.kamilszustak.read.data.model.Country
import pl.kamilszustak.read.domain.usecase.UseCase

interface GetAllCountriesUseCase : UseCase<List<Country>>