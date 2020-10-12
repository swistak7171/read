package pl.kamilszustak.read.data.di.module

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import pl.kamilszustak.read.data.di.qualifier.CollectionBookReference
import pl.kamilszustak.read.data.di.qualifier.QuoteReference
import pl.kamilszustak.read.data.di.qualifier.RootDatabaseReference
import pl.kamilszustak.read.model.data.CollectionBookEntity
import pl.kamilszustak.read.model.data.QuoteEntity
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
        reference.child(CollectionBookEntity.TABLE_NAME)

    @Provides
    @Singleton
    @QuoteReference
    fun provideQuoteReference(@RootDatabaseReference reference: DatabaseReference): DatabaseReference =
        reference.child(QuoteEntity.TABLE_NAME)
}