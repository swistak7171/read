package pl.kamilszustak.read.domain.usecase.volume

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.read.data.access.repository.VolumeRepository
import pl.kamilszustak.read.domain.access.usecase.volume.SearchForVolumesUseCase
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.model.mapper.volume.VolumeDtoMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchForVolumesUseCaseImpl @Inject constructor(
    private val repository: VolumeRepository,
    private val mapper: VolumeDtoMapper,
) : SearchForVolumesUseCase {

    override fun invoke(input: Map<VolumeSearchParameterType, String>): Flow<List<Volume>> =
        repository.searchAll(input)
            .map { mapper.mapAll(it) }
}