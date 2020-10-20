package pl.kamilszustak.read.model.domain

import java.util.*

abstract class Model : IdentifiedModel() {
    abstract val creationDate: Date
    abstract val modificationDate: Date
}