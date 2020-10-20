package pl.kamilszustak.read.model.domain

import android.os.Parcelable
import pl.kamilszustak.model.common.id.ModelId

abstract class IdentifiedModel : Parcelable {
    abstract val id: ModelId
}