package ru.eltech.contactnotes.data.repository

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.eltech.contactnotes.data.database.ContactsDbModel
import ru.eltech.contactnotes.data.database.NoteListDao
import ru.eltech.contactnotes.data.mapper.ContactNoteListMapper
import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import ru.eltech.contactnotes.domain.repository.ContactNoteListRepository
import javax.inject.Inject

class ContactNoteListRepositoryImpl @Inject constructor(
    private val application: Application,
    private val noteListDao: NoteListDao,
    private val mapper: ContactNoteListMapper,
) : ContactNoteListRepository {

    override suspend fun syncContactList() {
        noteListDao.deleteContactsListTable()
        val cursor = application.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        while (cursor?.moveToNext() == true) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                    ?: "null"
            val contact = ContactsDbModel(
                contactName = name,
                id = id
            )
            Log.d("MainViewModel", "$contact")
            noteListDao.insertContactItem(contact)
        }
        cursor?.close()

    }

    override suspend fun addNoteItem(item: ContactNoteItem) {
        noteListDao.insertNoteItem(mapper.noteItemToNoteDbModel(item))
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