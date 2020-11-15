package pl.kamilszustak.read.domain.access.usecase.scanner

import androidx.camera.core.ImageProxy
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface ReadBarcodeUseCase : CoroutineParametrizedUseCase<ImageProxy, Result<String?>>