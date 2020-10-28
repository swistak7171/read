package pl.kamilszustak.read.data.di.module

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import pl.kamilszustak.read.data.di.qualifier.BookCollection
import pl.kamilszustak.read.data.di.qualifier.QuoteCollection
import pl.kamilszustak.read.data.di.qualifier.ReadingLogCollection
import pl.kamilszustak.read.data.di.qualifier.RootDatabaseReference
import pl.kamilszustak.read.model.data.BookEntity
import pl.kamilszustak.read.model.data.DatabaseCollection
import pl.kamilszustak.read.model.data.QuoteEntity
import pl.kamilszustak.read.model.data.LogEntryEntity
import javax.inject.Singleton

@Module
class DatabaseModule {
    private val userId: String by lazy {
        Firebase.auth.currentUser?.uid ?: throw IllegalStateException("User is not signed in")
    }

    private fun createUserDatabaseCollection(
        databaseReference: DatabaseReference,
        collectionName: String,
        userIdProperty: String
    ): DatabaseCollection {
        val query = databaseReference.child(collectionName)
            .orderByChild(userIdProperty)
            .equalTo(userId)
            .apply {
                keepSynced(true)
            }

        return DatabaseCollection(
            name = collectionName,
            reference = query.ref,
            query = query
        )
    }

    @Provides
    @Singleton
    @RootDatabaseReference
    fun provideDatabaseReference(): DatabaseReference {
        val database = Firebase.database.apply {
            setPersistenceEnabled(true)
        }

        return database.reference
    }

    @Provides
    @Singleton
    @BookCollection
    fun provideBookCollection(@RootDatabaseReference reference: DatabaseReference): DatabaseCollection =
        createUserDatabaseCollection(reference, BookEntity.COLLECTION_NAME, BookEntity.USER_ID_PROPERTY)

    @Provides
    @Singleton
    @QuoteCollection
    fun provideQuoteCollection(@RootDatabaseReference reference: DatabaseReference): DatabaseCollection =
        createUserDatabaseCollection(reference, QuoteEntity.COLLECTION_NAME, QuoteEntity.USER_ID_PROPERTY)

    @Provides
    @Singleton
    @ReadingLogCollection
    fun provideReadingLogCollection(@RootDatabaseReference reference: DatabaseReference): DatabaseCollection =
        createUserDatabaseCollection(reference, LogEntryEntity.COLLECTION_NAME, LogEntryEntity.USER_ID_PROPERTY)
}