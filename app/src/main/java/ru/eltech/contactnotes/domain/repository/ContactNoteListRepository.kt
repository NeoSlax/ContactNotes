package ru.eltech.contactnotes.domain.repository

import androidx.lifecycle.LiveData
import ru.eltech.contactnotes.domain.entities.ContactNoteItem

interface ContactNoteListRepository {

    suspend fun addNoteItem(item: ContactNoteItem)

    suspend fun deleteNoteItem(contactNoteItem: ContactNoteItem)

    suspend fun editNoteItem(contactNoteItem: ContactNoteItem)

    suspend fun getNoteItem(id: Int): ContactNoteItem

    fun getNoteList(): LiveData<List<ContactNoteItem>>
}