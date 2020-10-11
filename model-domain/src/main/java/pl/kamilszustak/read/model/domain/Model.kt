package pl.kamilszustak.read.model.domain

import android.os.Parcelable
import java.util.Date

abstract class Model : Parcelable {
    abstract val id: String
    abstract val creationDate: Date
    abstract val modificationDate: Date
}