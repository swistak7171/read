package pl.kamilszustak.read.domain.access.usecase.volume

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.read.domain.access.usecase.ParametrizedUseCase
import pl.kamilszustak.read.model.domain.Volume

interface SearchForVolumesUseCase : ParametrizedUseCase<Map<VolumeSearchParameterType, String>, Flow<List<Volume>>>