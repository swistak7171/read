package pl.kamilszustak.read.domain.access.usecase.volume

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.Volume

interface GetVolumeUseCase : CoroutineParametrizedUseCase<String, Result<Volume?>>