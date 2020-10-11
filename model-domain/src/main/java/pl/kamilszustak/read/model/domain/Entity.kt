package pl.kamilszustak.read.model.domain

import com.google.firebase.database.PropertyName
import java.util.Date

abstract class Entity {
    @get:PropertyName(ID_PROPERTY)
    @set:PropertyName(ID_PROPERTY)
    var id: String = ""

    @get:PropertyName(CREATION_DATE_PROPERTY)
    @set:PropertyName(CREATION_DATE_PROPERTY)
    var creationDate: Date = Date()

    @get:PropertyName(MODIFICATION_DATE_PROPERTY)
    @set:PropertyName(MODIFICATION_DATE_PROPERTY)
    var modificationDate: Date = Date()

    companion object {
        const val ID_PROPERTY: String = "id"
        const val CREATION_DATE_PROPERTY: String = "creation_date"
        const val MODIFICATION_DATE_PROPERTY: String = "modification_date"
    }
}