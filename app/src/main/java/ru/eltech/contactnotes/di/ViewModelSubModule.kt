package ru.eltech.contactnotes.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.eltech.contactnotes.presentation.EditItemViewModel

@Module
interface ViewModelSubModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditItemViewModel::class)
    fun bindEditViewModel(viewModel: EditItemViewModel): ViewModel
}