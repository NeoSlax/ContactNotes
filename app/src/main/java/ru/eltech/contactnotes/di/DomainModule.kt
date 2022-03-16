package ru.eltech.contactnotes.di

import dagger.Binds
import dagger.Module
import ru.eltech.contactnotes.data.repository.ContactNoteListRepositoryImpl
import ru.eltech.contactnotes.di.ApplicationScope
import ru.eltech.contactnotes.domain.repository.ContactNoteListRepository

@Module
interface DomainModule {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: ContactNoteListRepositoryImpl): ContactNoteListRepository
}