package pl.kamilszustak.read.common.resource

import android.content.Context
interface ApplicationResource<T> {
    val id: Int
    fun get(context: Context): T
}

inline fun <T> Context.getResource(resource: ApplicationResource<T>): T =
    resource.get(this)