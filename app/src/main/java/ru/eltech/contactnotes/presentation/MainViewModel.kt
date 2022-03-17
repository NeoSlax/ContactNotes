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
import ru.eltech.contactnotes.domain.usecases.SyncContactListUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val application: Application,
    getNoteListUseCase: GetNoteListUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase,
    private val syncContactListUseCase: SyncContactListUseCase
) : ViewModel() {

    var contactNoteList = getNoteListUseCase()

    fun syncContact() {
        viewModelScope.launch {
            syncContactListUseCase()
        }

    }
}