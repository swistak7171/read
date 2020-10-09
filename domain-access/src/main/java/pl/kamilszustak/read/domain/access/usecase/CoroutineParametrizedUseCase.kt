package pl.kamilszustak.read.domain.access.usecase

interface CoroutineParametrizedUseCase<I, O> {
    suspend operator fun invoke(input: I): O
}