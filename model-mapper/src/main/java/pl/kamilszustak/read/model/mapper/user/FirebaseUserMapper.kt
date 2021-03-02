package pl.kamilszustak.read.model.mapper.user

import com.google.firebase.auth.FirebaseUser
import pl.kamilszustak.model.common.id.UserId
import pl.kamilszustak.read.model.domain.user.User
import pl.kamilszustak.read.model.mapper.Mapper
import java.util.*
import javax.inject.Inject

class FirebaseUserMapper @Inject constructor() : Mapper<FirebaseUser, User, Unit>() {
    override fun map(model: FirebaseUser, parameter: Unit): User {
        val id = UserId(model.uid)
        val creationDate = model.metadata?.creationTimestamp?.let { Date(it) }
        val lastSignInDate = model.metadata?.lastSignInTimestamp?.let { Date(it) }

        val displayName = model.displayName
        val email = model.email
        val number = model.phoneNumber
        val name = displayName.takeIf { !it.isNullOrBlank() }
        val emailAddress = email.takeIf { !it.isNullOrBlank() }
        val phoneNumber = number.takeIf { !it.isNullOrBlank() }

        return User(
            id = id,
            creationDate = creationDate,
            lastSignInDate = lastSignInDate,
            isAnonymous = model.isAnonymous,
            name = name,
            photoUrl = model.photoUrl,
            emailAddress = emailAddress,
            phoneNumber = phoneNumber
        )
    }
}