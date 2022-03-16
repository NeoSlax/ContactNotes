package ru.eltech.contactnotes.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eltech.contactnotes.data.database.NoteItemDbModel

@Dao
interface NoteListDao {

    @Query("SELECT * FROM contact_note_list")
    fun getNoteList(): LiveData<List<NoteItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(noteItem: NoteItemDbModel)

    @Query(" DELETE FROM contact_note_list WHERE id=:noteItemId")
    suspend fun deleteNoteItem(noteItemId: Int)

    @Query("SELECT * FROM contact_note_list WHERE id=:noteItemId LIMIT 1")
    suspend fun getNoteItem(noteItemId: Int): NoteItemDbModel
}