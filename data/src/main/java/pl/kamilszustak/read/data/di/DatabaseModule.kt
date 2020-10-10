package pl.kamilszustak.read.data.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import pl.kamilszustak.read.data.qualifier.CollectionBookReference
import pl.kamilszustak.read.data.qualifier.QuoteReference
import pl.kamilszustak.read.data.qualifier.RootDatabaseReference
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    @RootDatabaseReference
    fun provideDatabaseReference(): DatabaseReference {
        val database = Firebase.database.apply {
            setPersistenceEnabled(true)
        }

        return database.reference.apply {
            keepSynced(true)
        }
    }

    @Provides
    @Singleton
    @CollectionBookReference
    fun provideCollectionBookReference(@RootDatabaseReference reference: DatabaseReference): DatabaseReference =
        reference.child("collection_books")

    @Provides
    @Singleton
    @QuoteReference
    fun provideQuoteReference(@RootDatabaseReference reference: DatabaseReference): DatabaseReference =
        reference.child("quotes")
}