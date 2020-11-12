package pl.kamilszustak.read.domain.access.usecase.scanner

import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.text.Text
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface ReadTextUseCase : CoroutineParametrizedUseCase<ImageProxy, Result<Text>>