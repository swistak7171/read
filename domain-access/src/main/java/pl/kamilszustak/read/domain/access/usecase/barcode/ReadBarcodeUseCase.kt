package pl.kamilszustak.read.domain.access.usecase.barcode

import androidx.camera.core.ImageProxy
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface ReadBarcodeUseCase : CoroutineParametrizedUseCase<ImageProxy, Result<String>>