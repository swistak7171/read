package pl.kamilszustak.read.domain.access.usecase.device

import pl.kamilszustak.read.domain.access.usecase.UseCase

/**
 * For devices running Android M or higher you have to explicitly
 * ask user for permission (android.permission.READ_PHONE_STATE).
 */
interface GetPhoneNumberUseCase : UseCase<String?>