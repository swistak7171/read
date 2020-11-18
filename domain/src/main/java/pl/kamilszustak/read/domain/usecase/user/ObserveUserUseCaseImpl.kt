package pl.kamilszustak.read.domain.usecase.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.ObserveUserUseCase
import pl.kamilszustak.read.model.domain.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveUserUseCaseImpl @Inject constructor(
    private val getUser: GetUserUseCase,
) : ObserveUserUseCase {

    override fun invoke(): Flow<User> = flow {
        var lastUser: User? = null
        while(true) {
            val user = getUser()
            if (user != lastUser) {
                emit(user)
                lastUser = user
            }

            delay(50)
        }
    }.flowOn(Dispatchers.IO)
}