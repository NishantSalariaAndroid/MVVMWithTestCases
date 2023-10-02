package com.example.testapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.data.Repository
import kotlin.jvm.Throws

class RepoViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoViewModel(repository = Repository()) as T
    }
}