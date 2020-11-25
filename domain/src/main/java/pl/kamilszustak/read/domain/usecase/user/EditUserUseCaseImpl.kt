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
            val exception = IllegalStateException("User is not signed in")
            val user = auth.currentUser ?: return@withIOContext Result.failure(exception)

            with(user) {
                if (input.name != user.displayName) {
                    runCatching { updateProfile(request).await() }
                        .map { Unit }
                } else {
                    Result.success(Unit)
                }
            }
        }
    }
}