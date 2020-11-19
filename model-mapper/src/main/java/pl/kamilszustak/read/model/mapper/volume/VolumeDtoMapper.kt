package pl.kamilszustak.read.model.mapper.volume

import pl.kamilszustak.model.common.id.VolumeId
import pl.kamilszustak.model.network.VolumeDto
import pl.kamilszustak.read.common.util.tryOrNull
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.common.date.DateFormats
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class VolumeDtoMapper @Inject constructor(
    private val isbnDtoMapper: IsbnDtoMapper,
) : Mapper<VolumeDto, Volume>() {

    override fun map(model: VolumeDto): Volume {
        val id = VolumeId(model.id)
        val author = model.details.authors?.joinToString(", ")
        val subtitle = model.details.subtitle.takeIf { !it.isNullOrBlank() }
        val description = model.details.description.takeIf { !it.isNullOrBlank() }
        val publisher = model.details.publisher.takeIf { !it.isNullOrBlank() }
        val publicationDate = model.details.publicationDate.useOrNull { date ->
            val dashesNumber = date.count { it == '-' }
            val dateFormat = when (dashesNumber) {
                0 -> DateFormats.yearFormat
                1 -> DateFormats.yearMonthFormat
                else -> DateFormats.dateFormat
            }

            tryOrNull { dateFormat.parse(date) }
        }

        val isbns = model.details.isbns?.mapNotNull {
            tryOrNull { isbnDtoMapper.map(it) }
        }

        return Volume(
            id = id,
            title = model.details.title,
            subtitle = subtitle,
            author = author,
            description = description,
            publisher = publisher,
            publicationDate = publicationDate,
            pagesNumber = model.details.pagesNumber,
            isbns = isbns,
            coverImageUrl = model.details.coverImageUrl?.thumbnailUrl ?: Book.COVER_IMAGE_PLACEHOLDER_URL
        )
    }
}