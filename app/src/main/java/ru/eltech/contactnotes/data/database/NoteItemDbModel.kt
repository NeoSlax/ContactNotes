package ru.eltech.contactnotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class NoteItemDbModel(
    val id: Int,
    val contactName: String,
    @Relation(
        parentColumn = "id", entityColumn = "id", entity = NotesDbModel::class, projection =
        ["note"]
    )
    val note: String? = null
)
