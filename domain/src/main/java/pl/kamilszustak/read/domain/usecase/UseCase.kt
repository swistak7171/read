package pl.kamilszustak.read.domain.usecase

interface UseCase<O> {
    operator fun invoke(): O
}