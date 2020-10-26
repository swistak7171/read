package pl.kamilszustak.read.data.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query

data class DatabaseCollection(
    val name: String,
    val reference: DatabaseReference,
    val query: Query,
)
