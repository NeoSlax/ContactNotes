package ru.eltech.contactnotes.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import ru.eltech.contactnotes.domain.usecases.AddNoteItemUseCase
import ru.eltech.contactnotes.domain.usecases.GetNoteItemByIdUseCase
import javax.inject.Inject

class EditItemViewModel @Inject constructor(
    private val id: Int,
    private val getNoteItemByIdUseCase: GetNoteItemByIdUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase
) : ViewModel() {

    private lateinit var name: String

    private val _noteItem = MutableLiveData<ContactNoteItem>()
    val noteItem: LiveData<ContactNoteItem>
        get() = _noteItem

    private val _isFinished = MutableLiveData<Unit>()
    val isFinished: LiveData<Unit>
        get() = _isFinished

    init {
        viewModelScope.launch {
            val item = getNoteItemByIdUseCase(id)
            name = item.contactName
            _noteItem.value = item
        }
    }

    fun addNoteItem(note: String) {
        val item = ContactNoteItem(name, note, id)
        viewModelScope.launch {
            addNoteItemUseCase(item)
            _isFinished.value = Unit
        }

    }


}