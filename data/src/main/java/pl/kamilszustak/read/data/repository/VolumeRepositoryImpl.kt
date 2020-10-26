package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.model.network.VolumeDto
import pl.kamilszustak.read.VolumeSearchParameterFactory
import pl.kamilszustak.read.data.access.repository.VolumeRepository
import pl.kamilszustak.read.service.GoogleBooksApiService
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VolumeRepositoryImpl @Inject constructor(
    private val apiService: GoogleBooksApiService,
    private val parameterFactory: VolumeSearchParameterFactory,
) : VolumeRepository {

    override suspend fun getAll(parameters: Map<VolumeSearchParameterType, String>): Result<List<VolumeDto>?> =
        runCatching {
            val query = parameterFactory.create(parameters)
            val response = apiService.searchForVolumes(query)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                body.volumes
            } else {
                throw HttpException(response)
            }
        }

    override fun observeAll(parameters: Map<VolumeSearchParameterType, String>): Flow<List<VolumeDto>?> {
        return flow {
            getAll(parameters)
                .onSuccess { emit(it) }
        }
    }
}