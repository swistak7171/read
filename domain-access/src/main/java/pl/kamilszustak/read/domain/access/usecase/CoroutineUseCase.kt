package pl.kamilszustak.read.domain.access.usecase

interface CoroutineUseCase<O> {
    suspend operator fun invoke(): O
}