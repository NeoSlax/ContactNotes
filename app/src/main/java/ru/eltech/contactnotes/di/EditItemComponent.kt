package ru.eltech.contactnotes.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.eltech.contactnotes.presentation.EditItemActivity

@Subcomponent(modules = [
    ViewModelSubModule::class
])
interface EditItemComponent {

    fun inject(activity: EditItemActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance id: Int
        ): EditItemComponent
    }
}