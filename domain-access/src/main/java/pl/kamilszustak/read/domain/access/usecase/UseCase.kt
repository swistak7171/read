package pl.kamilszustak.read.domain.access.usecase

interface UseCase<O> : BaseUseCase {
    operator fun invoke(): O
}