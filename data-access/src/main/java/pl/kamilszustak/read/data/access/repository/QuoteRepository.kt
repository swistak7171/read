package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.data.QuoteEntity

interface QuoteRepository {
    suspend fun add(quote: QuoteEntity): Result<Unit>
}