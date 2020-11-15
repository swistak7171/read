package pl.kamilszustak.read.domain.access.usecase.user

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.user.ProfileDetails

interface EditUserUseCase : CoroutineParametrizedUseCase<ProfileDetails, Result<Unit>>