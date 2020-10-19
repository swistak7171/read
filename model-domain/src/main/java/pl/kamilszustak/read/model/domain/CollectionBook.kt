package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.CollectionBookId
import java.util.Date
import kotlin.math.roundToInt

@Parcelize
data class CollectionBook(
    override val id: CollectionBookId = CollectionBookId(),
    override val creationDate: Date = Date(),
    override val modificationDate: Date = Date(),
    val title: String,
    val author: String,
    val numberOfPages: Int,
    val publicationDate: Date?,
    val isbn: String?,
    val description: String?,
    val coverImageUrl: String? = COVER_IMAGE_PLACEHOLDER_URL,
    val readPages: Int = 0
) : Model() {

    val progress: Float
        get() = (readPages / numberOfPages.toFloat())

    val progressPercentage: Int
        get() = (progress * 100).roundToInt()

    companion object {
        const val COVER_IMAGE_PLACEHOLDER_URL: String = "https://i.stack.imgur.com/1hvpD.jpg"
    }
}