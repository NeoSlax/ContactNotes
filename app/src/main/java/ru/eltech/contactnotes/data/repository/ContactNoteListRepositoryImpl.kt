package ru.eltech.contactnotes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.eltech.contactnotes.data.database.NoteListDao
import ru.eltech.contactnotes.data.mapper.ContactNoteListMapper
import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import ru.eltech.contactnotes.domain.repository.ContactNoteListRepository
import javax.inject.Inject

class ContactNoteListRepositoryImpl @Inject constructor(
    private val noteListDao: NoteListDao,
    private val mapper: ContactNoteListMapper,
) : ContactNoteListRepository {


    override suspend fun addNoteItem(item: ContactNoteItem) {
        noteListDao.insertNoteItem(mapper.noteItemToNoteItemDb(item))
    }

    override suspend fun deleteNoteItem(contactNoteItem: ContactNoteItem) {
        noteListDao.deleteNoteItem(contactNoteItem.id)
    }

    override suspend fun editNoteItem(contactNoteItem: ContactNoteItem) {
        addNoteItem(contactNoteItem)
    }

    override suspend fun getNoteItem(id: Int): ContactNoteItem {
        val dbModel = noteListDao.getNoteItem(id)
        return mapper.noteItemDbToNoteItem(dbModel)
    }

    override fun getNoteList(): LiveData<List<ContactNoteItem>> =
        MediatorLiveData<List<ContactNoteItem>>().apply {
            addSource(noteListDao.getNoteList()) {
                value = mapper.noteItemDbListToNoteList(it)
            }
        }

}