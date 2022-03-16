package ru.eltech.contactnotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_note_list")
data class NoteItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val note: String
)
