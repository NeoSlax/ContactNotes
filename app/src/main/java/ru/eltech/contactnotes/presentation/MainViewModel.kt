package ru.eltech.contactnotes.presentation

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import ru.eltech.contactnotes.domain.usecases.AddNoteItemUseCase
import ru.eltech.contactnotes.domain.usecases.GetNoteListUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val application: Application,
    getNoteListUseCase: GetNoteListUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase
) : ViewModel() {

    var contactNoteList = getNoteListUseCase()

    fun syncContact() {

        viewModelScope.launch {
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
                val noteItem = ContactNoteItem(
                    contactName = name,
                    note = "",
                    id = id
                )
                Log.d("MainViewModel", "$noteItem")
                addNoteItemUseCase(noteItem)
            }
            cursor?.close()

        }

    }
}