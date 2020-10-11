package pl.kamilszustak.read.model.domain

import com.google.firebase.auth.FirebaseUser

data class Session(
    val user: FirebaseUser,
)