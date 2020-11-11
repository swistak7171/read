package pl.kamilszustak.read.domain.usecase.quote

import com.zedlabs.pastelplaceholder.LightColors
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteColorsUseCase
import javax.inject.Inject

class GetQuoteColorsUseCaseImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
): GetQuoteColorsUseCase {

    override fun invoke(): List<Int> {
        return LightColors.list.map { resourceId ->
            resourceProvider.getColor(resourceId)
        }
    }
}