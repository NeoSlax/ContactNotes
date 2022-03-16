package ru.eltech.contactnotes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter

import ru.eltech.contactnotes.R
import ru.eltech.contactnotes.databinding.ItemNoteBinding
import ru.eltech.contactnotes.domain.entities.ContactNoteItem

class NoteListAdapter : ListAdapter<ContactNoteItem, ContactListViewHolder>(NoteItemDiffCallback) {


    var onItemLongClickListener: ((contactNoteItem: ContactNoteItem) -> Unit)? = null
    var onItemClickListener: ((contactNoteItem: ContactNoteItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {

        val resourceLayout =  R.layout.item_note

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            resourceLayout,
            parent,
            false
        )
        return ContactListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val contactNoteItem = getItem(position)
        val binding = holder.binding
        when (binding) {
            is ItemNoteBinding -> {
                binding.contactItem = contactNoteItem
            }

        }
        with(binding.root) {
            setOnLongClickListener {
                onItemLongClickListener?.invoke(contactNoteItem)
                true
            }
            setOnClickListener {
                onItemClickListener?.invoke(contactNoteItem)

            }
        }
    }

    companion object {
        const val MAX_POOL_SIZE = 16
    }

}