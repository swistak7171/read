package pl.kamilszustak.read.model.domain

import android.os.Parcelable
import pl.kamilszustak.model.common.id.ModelId
import java.util.Date

abstract class Model : Parcelable {
    abstract val id: ModelId
    abstract val creationDate: Date
    abstract val modificationDate: Date
}