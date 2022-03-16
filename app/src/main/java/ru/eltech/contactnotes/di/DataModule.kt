package ru.eltech.contactnotes.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.eltech.contactnotes.data.database.AppDatabase
import ru.eltech.contactnotes.data.database.NoteListDao
import ru.eltech.contactnotes.di.ApplicationScope

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideNoteListDao(application: Application): NoteListDao {
        return AppDatabase.getInstance(application).shopListDao()
    }
}