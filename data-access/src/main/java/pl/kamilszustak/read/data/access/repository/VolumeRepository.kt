package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.model.network.VolumeDto

interface VolumeRepository {
    fun searchAll(parameters: Map<VolumeSearchParameterType, String>): Flow<List<VolumeDto>>
}