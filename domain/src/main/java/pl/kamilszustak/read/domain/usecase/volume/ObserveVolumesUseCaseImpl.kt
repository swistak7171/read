package pl.kamilszustak.read.domain.usecase.volume

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.VolumeRepository
import pl.kamilszustak.read.domain.access.usecase.volume.ObserveVolumesUseCase
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.model.mapper.volume.VolumeDtoMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveVolumesUseCaseImpl @Inject constructor(
    private val repository: VolumeRepository,
    private val mapper: VolumeDtoMapper,
) : ObserveVolumesUseCase {

    override fun invoke(input: Map<VolumeSearchParameterType, String>): Flow<List<Volume>?> =
        repository.observeAll(input)
            .map { volumes ->
                volumes.useOrNull {
                    mapper.mapAll(it, Unit)
                }
            }
}