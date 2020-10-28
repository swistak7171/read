package pl.kamilszustak.read.data.util

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import pl.kamilszustak.read.model.data.Entity
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

inline fun <reified E> valueEventFlow(
    query: Query,
    noinline onDataChange: (snapshot: DataSnapshot) -> E?
) = callbackFlow<E> {
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

inline fun <reified E : Entity> entityFlow(query: Query): Flow<E> = valueEventFlow<E>(
    query = query,
    onDataChange = { snapshot ->
        val value = snapshot.getValue<E>()
        val key = snapshot.key
        if (key != null) {
            value?.id = key
        }

        value
    }
)

inline fun <reified E : Entity> entityFlow(crossinline block: () -> Query): Flow<E> =
    entityFlow(block())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified E : Entity> entityListFlow(query: Query): Flow<List<E>> = valueEventFlow(
    query = query,
    onDataChange = { snapshot ->
        buildList<E> {
            snapshot.children.forEach { child ->
                val value = child?.getValue<E>()
                val key = child?.key

                if (value != null && key != null) {
                    value.id = key
                    add(value)
                }
            }
        }
    }
)

inline fun <reified E : Entity> entityListFlow(crossinline block: () -> Query): Flow<List<E>> =
    entityListFlow(block())

suspend inline fun <reified E : Entity> readEntity(query: Query): E? = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val value = snapshot.getValue<E>()
                val key = snapshot.key
                if (key != null) {
                    value?.id = key
                }

                continuation.resume(value)
            } catch (throwable: Throwable) {
                continuation.resumeWithException(throwable)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            continuation.resumeWithException(error.toException())
        }
    }

    query.addListenerForSingleValueEvent(listener)
    continuation.invokeOnCancellation {
        query.removeEventListener(listener)
    }
}

suspend inline fun <reified E : Entity> readEntity(crossinline block: () -> Query): E? =
    readEntity(block())

@OptIn(ExperimentalStdlibApi::class)
suspend inline fun <reified E : Entity> readEntityList(query: Query): List<E> = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val values = buildEntityList<E>(snapshot)
                continuation.resume(values)
            } catch (throwable: Throwable) {
                continuation.resumeWithException(throwable)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            continuation.resumeWithException(error.toException())
        }
    }

    query.addListenerForSingleValueEvent(listener)
    continuation.invokeOnCancellation {
        query.removeEventListener(listener)
    }
}

suspend inline fun <reified E : Entity> readEntityList(crossinline block: () -> Query): List<E> =
    readEntityList(block())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified E : Entity> buildEntityList(snapshot: DataSnapshot): List<E> =
    buildList {
        snapshot.children.forEach { child ->
            val value = child?.getValue<E>()
            val key = child?.key

            if (value != null && key != null) {
                value.id = key
                add(value)
            }
        }
    }