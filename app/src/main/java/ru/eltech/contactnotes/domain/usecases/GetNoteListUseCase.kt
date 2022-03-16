package ru.eltech.contactnotes.domain.usecases

import androidx.lifecycle.LiveData
import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import ru.eltech.contactnotes.domain.repository.ContactNoteListRepository
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(private val contactNoteListRepository: ContactNoteListRepository) {
    operator fun invoke(): LiveData<List<ContactNoteItem>> = contactNoteListRepository.getNoteList()
}