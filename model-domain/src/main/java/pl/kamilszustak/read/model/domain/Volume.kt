package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.VolumeId
import java.util.*

@Parcelize
data class Volume(
    override val id: VolumeId,
    val title: String,
    val subtitle: String?,
    val author: String?,
    val description: String?,
    val publisher: String?,
    val publicationDate: Date?,
    val pagesNumber: Int?,
    val isbns: List<Isbn>?,
    val coverImageUrl: String,
) : IdentifiedModel()