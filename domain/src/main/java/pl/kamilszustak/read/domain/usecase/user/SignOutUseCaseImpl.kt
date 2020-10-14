package pl.kamilszustak.read.domain.usecase.user

import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignOutUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SignOutUseCase {

    override fun invoke() {
        auth.signOut()
    }
}