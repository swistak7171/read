package pl.kamilszustak.read.domain.usecase.volume

import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.VolumeRepository
import pl.kamilszustak.read.domain.access.usecase.volume.GetVolumeUseCase
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.model.mapper.volume.VolumeDtoMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVolumeUseCaseImpl @Inject constructor(
    private val volumeRepository: VolumeRepository,
    private val mapper: VolumeDtoMapper,
) : GetVolumeUseCase {

    override suspend fun invoke(input: String): Result<Volume?> {
        val parameters = mapOf(VolumeSearchParameterType.ISBN to input)

        return volumeRepository.getAll(parameters)
            .map { volumes ->
                volumes?.firstOrNull()
                    .useOrNull { mapper.map(it) }
            }
    }
}