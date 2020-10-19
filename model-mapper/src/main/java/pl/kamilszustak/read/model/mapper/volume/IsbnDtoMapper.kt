package pl.kamilszustak.read.model.mapper.volume

import pl.kamilszustak.model.network.IsbnDto
import pl.kamilszustak.read.model.domain.Isbn
import pl.kamilszustak.read.model.domain.IsbnType
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class IsbnDtoMapper @Inject constructor() : Mapper<IsbnDto, Isbn>() {
    override fun map(model: IsbnDto): Isbn {
        val type = when (model.type) {
            "ISBN_10" -> IsbnType.ISBN_10
            "ISBN_13" -> IsbnType.ISBN_13
            else -> throw IllegalArgumentException("Invalid ISBN type: ${model.type}")
        }

        return Isbn(
            type = type,
            value = model.value
        )
    }
}