package pl.kamilszustak.read.domain.access.usecase.user

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase
import pl.kamilszustak.read.model.domain.user.User

interface ObserveUserUseCase : UseCase<Flow<User>>