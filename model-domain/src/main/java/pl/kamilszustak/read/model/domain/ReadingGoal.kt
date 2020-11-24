package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.ReadingGoalId
import java.util.*

@Parcelize
data class ReadingGoal(
    override val id: ReadingGoalId = ReadingGoalId(),
    override val creationDate: Date = Date(),
    override val modificationDate: Date = Date(),
    val pagesNumber: Int,
    val reminderTime: Int
) : Model()