package pl.kamilszustak.read.data.util

import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.kamilszustak.read.model.data.Entity
import timber.log.Timber

inline fun <reified T> valueEventFlow(
    query: Query,
    noinline onDataChange: (snapshot: DataSnapshot) -> T?
) = callbackFlow<T> {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val value = onDataChange(snapshot)
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

        query.addValueEventListener(listener)

        awaitClose {
            query.removeEventListener(listener)
        }
    }

inline fun <reified T : Entity> entityFlow(crossinline block: () -> Query): Flow<T> =
    valueEventFlow<T>(
        query = block(),
        onDataChange = { snapshot ->
            snapshot.getValue<T>()
        }
    )

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Entity> entityListFlow(crossinline block: () -> Query): Flow<List<T>> =
    valueEventFlow(
        query = block(),
        onDataChange = { snapshot ->
            buildList<T> {
                snapshot.children.forEach { child ->
                    val value = child?.getValue<T>()
                    val key = child?.key

                    if (value != null && key != null) {
                        value.id = key
                        add(value)
                    }
                }
            }
        }
    )
