package pl.kamilszustak.read.domain.usecase

interface GenericUseCase<I, O> {
    operator fun invoke(input: I): O
}