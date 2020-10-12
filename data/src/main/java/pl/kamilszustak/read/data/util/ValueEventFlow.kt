package pl.kamilszustak.read.data.util

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.kamilszustak.read.model.data.CollectionBookEntity
import timber.log.Timber

fun <T> valueEventFlow(block: () -> DatabaseReference): Flow<T> = callbackFlow {
    val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val value = snapshot.getValue<List<CollectionBookEntity>>()
                if (value != null) {
                    offer(value)
                }
            } catch (throwable: Throwable) {
                Timber.e(throwable)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            close(error.toException())
        }
    }

    val databaseReference= block()
        .also { it.addValueEventListener(listener) }

    awaitClose {
        databaseReference.removeEventListener(listener)
    }
}