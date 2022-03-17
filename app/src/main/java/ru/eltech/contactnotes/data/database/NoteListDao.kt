package ru.eltech.contactnotes.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eltech.contactnotes.data.database.NoteItemDbModel

@Dao
interface NoteListDao {

    @Query("SELECT * FROM contacts_table")
    fun getNoteList(): LiveData<List<NoteItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(noteItem: NotesDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContactItem(contactItem: ContactsDbModel)

    @Query(" DELETE FROM contacts_table")
    suspend fun deleteContactsListTable()

    @Query("SELECT * FROM contacts_table WHERE id=:noteItemId LIMIT 1")
    suspend fun getNoteItem(noteItemId: Int): NoteItemDbModel
}