package pl.kamilszustak.read.domain.access.usecase.user

import com.google.firebase.auth.FirebaseUser
import pl.kamilszustak.read.domain.access.usecase.UseCase

interface GetUserUseCase : UseCase<FirebaseUser>