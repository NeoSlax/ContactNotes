package ru.eltech.contactnotes.domain.repository

import androidx.lifecycle.LiveData
import ru.eltech.contactnotes.domain.entities.ContactNoteItem

interface ContactNoteListRepository {

    suspend fun syncContactList()

    suspend fun addNoteItem(item: ContactNoteItem)

    suspend fun getNoteItem(id: Int): ContactNoteItem

    fun getNoteList(): LiveData<List<ContactNoteItem>>
}