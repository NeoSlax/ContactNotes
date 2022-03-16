package ru.eltech.contactnotes.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.eltech.contactnotes.domain.entities.ContactNoteItem

object NoteItemDiffCallback : DiffUtil.ItemCallback<ContactNoteItem>() {
    override fun areItemsTheSame(oldItem: ContactNoteItem, newItem: ContactNoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ContactNoteItem, newItem: ContactNoteItem): Boolean {
        return oldItem == newItem
    }
}