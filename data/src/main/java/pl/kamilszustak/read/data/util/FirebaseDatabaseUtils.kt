package pl.kamilszustak.read.data.util

import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import pl.kamilszustak.read.model.data.Entity
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

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

inline fun <reified T : Entity> entityFlow(query: Query): Flow<T> = valueEventFlow<T>(
    query = query,
    onDataChange = { snapshot ->
        val value = snapshot.getValue<T>()
        val key = snapshot.key
        if (key != null) {
            value?.id = key
        }

        value
    }
)

inline fun <reified T : Entity> entityFlow(crossinline block: () -> Query): Flow<T> =
    entityFlow(block())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Entity> entityListFlow(query: Query): Flow<List<T>> = valueEventFlow(
    query = query,
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

inline fun <reified T : Entity> entityListFlow(crossinline block: () -> Query): Flow<List<T>> =
    entityListFlow(block())

suspend inline fun <reified T : Entity> readEntity(query: Query): T? = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val value = snapshot.getValue<T>()
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

suspend inline fun <reified T : Entity> readEntity(crossinline block: () -> Query): T? =
    readEntity(block())

@OptIn(ExperimentalStdlibApi::class)
suspend inline fun <reified T : Entity> readEntityList(query: Query): List<T> = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val values = buildEntityList<T>(snapshot)
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

suspend inline fun <reified T : Entity> readEntityList(crossinline block: () -> Query): List<T> =
    readEntityList(block())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Entity> buildEntityList(snapshot: DataSnapshot): List<T> =
    buildList {
        snapshot.children.forEach { child ->
            val value = child?.getValue<T>()
            val key = child?.key

            if (value != null && key != null) {
                value.id = key
                add(value)
            }
        }
    }