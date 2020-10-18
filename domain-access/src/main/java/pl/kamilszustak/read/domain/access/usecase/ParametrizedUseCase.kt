package pl.kamilszustak.read.domain.access.usecase

interface ParametrizedUseCase<I, O> : BaseUseCase {
    operator fun invoke(input: I): O
}