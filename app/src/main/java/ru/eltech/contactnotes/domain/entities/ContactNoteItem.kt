package ru.eltech.contactnotes.domain.entities

data class ContactNoteItem(
    val contactName: String,
    val note: String,
    var id: Int = UNDEFINED_ID
){
    companion object {
        const val UNDEFINED_ID = 0
    }
}
