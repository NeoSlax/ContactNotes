package ru.eltech.contactnotes.data.mapper

import ru.eltech.contactnotes.data.database.NoteItemDbModel
import ru.eltech.contactnotes.data.database.NotesDbModel
import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import javax.inject.Inject

class ContactNoteListMapper @Inject constructor() {

    fun noteItemToNoteItemDb(noteItem: ContactNoteItem) = NoteItemDbModel(
        id = noteItem.id,
        contactName = noteItem.contactName,
        note = noteItem.note
    )

    fun noteItemDbToNoteItem(noteItemDb: NoteItemDbModel) = ContactNoteItem(
        id = noteItemDb.id,
        contactName = noteItemDb.contactName,
        note = noteItemDb?.note ?: ""
    )

    fun noteItemDbListToNoteList(noteList: List<NoteItemDbModel>) = noteList.map {
        noteItemDbToNoteItem(it)
    }

    fun noteItemToNoteDbModel(note: ContactNoteItem) = NotesDbModel(
        id = note.id,
        note = note.note
    )
}