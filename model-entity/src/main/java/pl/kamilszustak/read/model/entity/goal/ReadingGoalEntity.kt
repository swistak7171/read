package pl.kamilszustak.read.model.entity.goal

import com.google.firebase.database.PropertyName
import pl.kamilszustak.read.model.entity.Entity

data class ReadingGoalEntity(
    @get:PropertyName(TYPE_NAME_PROPERTY)
    @set:PropertyName(TYPE_NAME_PROPERTY)
    var typeName: String = "",

    @get:PropertyName(PAGES_NUMBER_PROPERTY)
    @set:PropertyName(PAGES_NUMBER_PROPERTY)
    var pagesNumber: Int = -1,

    @get:PropertyName(REMINDER_TIME_PROPERTY)
    @set:PropertyName(REMINDER_TIME_PROPERTY)
    var reminderTime: String = "",

    @get:PropertyName(USER_ID_PROPERTY)
    @set:PropertyName(USER_ID_PROPERTY)
    var userId: String = "",
) : Entity() {

    companion object {
        const val COLLECTION_NAME: String = "reading_goals"
        const val PAGES_NUMBER_PROPERTY: String = "pages_number"
        const val REMINDER_TIME_PROPERTY: String = "reminder_time"
        const val TYPE_NAME_PROPERTY: String = "type_name"
        const val USER_ID_PROPERTY: String = "user_id"
    }
}
