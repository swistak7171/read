package pl.kamilszustak.read.domain.usecase.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : GetUserUseCase {

    override fun invoke(): FirebaseUser =
        auth.currentUser ?: throw IllegalStateException("User is not signed in")
}