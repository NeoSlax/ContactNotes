package ru.eltech.contactnotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NotesDbModel(
    @PrimaryKey
    val id: Int,
    val note: String
)
