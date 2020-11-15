package pl.kamilszustak.read.model.mapper.user

import com.google.firebase.auth.FirebaseUser
import pl.kamilszustak.model.common.id.UserId
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.model.domain.User
import pl.kamilszustak.read.model.mapper.Mapper
import java.util.*
import javax.inject.Inject

class FirebaseUserMapper @Inject constructor() : Mapper<FirebaseUser, User>() {
    override fun map(model: FirebaseUser): User {
        val id = UserId(model.uid)
        val creationDate = model.metadata?.creationTimestamp.useOrNull { Date(it) }
        val lastSignInDate = model.metadata?.lastSignInTimestamp.useOrNull { Date(it) }

        val displayName = model.displayName
        val email = model.email
        val number = model.phoneNumber
        val name = if (!displayName.isNullOrBlank()) displayName else null
        val emailAddress = if (!email.isNullOrBlank()) email else null
        val phoneNumber = if (!number.isNullOrBlank()) number else null

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