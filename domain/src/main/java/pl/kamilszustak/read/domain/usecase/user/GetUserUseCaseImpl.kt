package pl.kamilszustak.read.domain.usecase.user

import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.domain.User
import pl.kamilszustak.read.model.mapper.user.FirebaseUserMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val mapper: FirebaseUserMapper,
) : GetUserUseCase {

    override fun invoke(): User =
        auth.currentUser.useOrNull { mapper.map(it) }
            ?: throw IllegalStateException("User is not signed in")
}