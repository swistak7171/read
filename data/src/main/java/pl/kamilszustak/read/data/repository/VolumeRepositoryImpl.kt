package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.model.network.VolumeDto
import pl.kamilszustak.read.VolumeSearchParameterFactory
import pl.kamilszustak.read.data.access.repository.VolumeRepository
import pl.kamilszustak.read.service.GoogleBooksApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VolumeRepositoryImpl @Inject constructor(
    private val apiService: GoogleBooksApiService,
    private val parameterFactory: VolumeSearchParameterFactory,
) : VolumeRepository {

    override fun searchAll(parameters: Map<VolumeSearchParameterType, String>): Flow<List<VolumeDto>> {
        val query = parameterFactory.create(parameters)

        return flow {
            val response = apiService.searchForVolumes(query)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(body.volumes)
            }
        }
    }
}