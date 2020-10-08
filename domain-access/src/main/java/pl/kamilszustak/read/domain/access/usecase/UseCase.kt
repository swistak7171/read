package pl.kamilszustak.read.domain.access.usecase

interface UseCase<O> {
    operator fun invoke(): O
}