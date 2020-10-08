package pl.kamilszustak.read.domain.access.usecase

interface ParametrizedUseCase<I, O> {
    operator fun invoke(input: I): O
}