package pl.kamilszustak.read.domain.usecase.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.domain.access.usecase.user.EditUserUseCase
import pl.kamilszustak.read.model.domain.user.ProfileDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : EditUserUseCase {

    override suspend fun invoke(input: ProfileDetails): Result<Unit> {
        val request = userProfileChangeRequest {
            this.displayName = input.name
        }

        return withIOContext {
            val notSignedInException = IllegalStateException("User is not signed in")
            val user = auth.currentUser ?: return@withIOContext Result.failure(notSignedInException)
            val results = mutableListOf<Result<Unit>>()

            with(user) {
                if (input.name != user.displayName) {
                    user.reauthenticate()
                    runCatching { updateProfile(request).await() }
                        .map { Unit }
                        .let { results.add(it) }
                }

                val emailAddress = input.emailAddress
                if (emailAddress != null && emailAddress != user.email) {
                    runCatching { updateEmail(emailAddress).await() }
                        .map { Unit }
                        .let { results.add(it) }
                }

                val failure = results.find { it.isFailure }

                failure ?: Result.success(Unit)
            }
        }
    }
}