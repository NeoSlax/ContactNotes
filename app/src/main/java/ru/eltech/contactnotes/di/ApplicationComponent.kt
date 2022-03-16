package ru.eltech.contactnotes.di

import android.app.Application

import dagger.BindsInstance
import dagger.Component
import ru.eltech.contactnotes.presentation.EditItemActivity
import ru.eltech.contactnotes.presentation.MainActivity
import ru.eltech.contactnotes.presentation.MainViewModel

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {


    fun inject(activity: MainActivity)

    fun editItemComponentFactory(): EditItemComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}