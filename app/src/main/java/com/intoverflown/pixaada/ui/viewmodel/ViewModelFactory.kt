package com.intoverflown.pixaada.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intoverflown.pixaada.repository.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    // ViewModel depends on ViewModelProviders utility because we can not create ViewModel on our own
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}