package pl.kamilszustak.read.model.domain

import android.net.Uri
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.UserId
import java.util.*

@Parcelize
data class User(
    override val id: UserId,
    val creationDate: Date?,
    val lastSignInDate: Date?,
    val isAnonymous: Boolean,
    val name: String?,
    val photoUrl: Uri?,
    val emailAddress: String?,
    val phoneNumber: String?,
) : IdentifiedModel()
