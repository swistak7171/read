package pl.kamilszustak.read.model.domain

import java.util.Date

abstract class Model {
    var id: String = ""
    var creationDate: Date = Date()
    var modificationDate: Date = Date()
}