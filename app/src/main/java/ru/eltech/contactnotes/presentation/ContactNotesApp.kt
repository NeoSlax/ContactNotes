package ru.eltech.contactnotes.presentation

import android.app.Application
import ru.eltech.contactnotes.di.DaggerApplicationComponent

class ContactNotesApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}