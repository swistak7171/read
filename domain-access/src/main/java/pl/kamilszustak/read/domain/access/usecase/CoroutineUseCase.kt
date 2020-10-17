package pl.kamilszustak.read.domain.access.usecase

interface CoroutineUseCase<O> : BaseUseCase {
    suspend operator fun invoke(): O
}