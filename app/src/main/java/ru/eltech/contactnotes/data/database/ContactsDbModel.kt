package ru.eltech.contactnotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class ContactsDbModel(
    @PrimaryKey
    val id: Int,
    val contactName: String
)
