package pl.kamilszustak.read.model.mapper.volume

import pl.kamilszustak.model.common.id.VolumeId
import pl.kamilszustak.model.network.VolumeDto
import pl.kamilszustak.read.common.util.tryOrNull
import pl.kamilszustak.read.domain.access.DateFormats
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class VolumeDtoMapper @Inject constructor(
    private val dateFormats: DateFormats,
    private val isbnDtoMapper: IsbnDtoMapper,
) : Mapper<VolumeDto, Volume>() {

    override fun map(model: VolumeDto): Volume {
        val id = VolumeId(model.id)
        val author = model.details.authors.joinToString(", ")
        val subtitle = model.details.subtitle.takeIf { it.isNotBlank() }
        val description = model.details.description.takeIf { it.isNotBlank() }
        val publisher = model.details.publisher.takeIf { it.isNotBlank() }
        val dashesNumber = model.details.publicationDate.count { it == '-' }
        val dateFormat = when (dashesNumber) {
            0 -> dateFormats.yearFormat
            1 -> dateFormats.yearMonthFormat
            else -> dateFormats.dateFormat
        }

        val publicationDate = tryOrNull {
            dateFormat.parse(model.details.publicationDate)
        }

        val isbns = model.details.isbns.map { isbnDtoMapper.map(it) }

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
            coverImageUrl = model.details.coverImageUrl.thumbnailUrl
        )
    }
}