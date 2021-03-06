package ru.eltech.contactnotes.domain.usecases

import ru.eltech.contactnotes.domain.entities.ContactNoteItem
import ru.eltech.contactnotes.domain.repository.ContactNoteListRepository
import javax.inject.Inject

class AddNoteItemUseCase @Inject constructor(private val contactNoteListRepository: ContactNoteListRepository) {
    suspend operator fun invoke(item: ContactNoteItem) = contactNoteListRepository.addNoteItem(item)
}