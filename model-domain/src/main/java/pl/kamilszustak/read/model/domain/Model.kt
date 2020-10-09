package pl.kamilszustak.read.model.domain

import java.util.Date

abstract class Model {
    var id: Long = 0
    var creationDate: Date = Date()
    var modificationDate: Date = Date()
}