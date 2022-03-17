package ru.eltech.contactnotes.domain.usecases

import ru.eltech.contactnotes.domain.repository.ContactNoteListRepository
import javax.inject.Inject

class SyncContactListUseCase @Inject constructor(
    private val contactNoteListRepository: ContactNoteListRepository
){
    suspend operator fun invoke() = contactNoteListRepository.syncContactList()
}