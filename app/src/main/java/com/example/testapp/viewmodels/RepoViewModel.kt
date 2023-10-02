package com.example.testapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.data.GithubRepoModel
import com.example.testapp.data.Repository
import com.example.testapp.data.adapter.RepoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoViewModel (private val repository: Repository) : ViewModel() {
    private val _repositories = MutableLiveData<ArrayList<GithubRepoModel>>()
    val repositories: LiveData<ArrayList<GithubRepoModel>> get() = _repositories

    var dataAdapter: RepoAdapter = RepoAdapter()

    fun getAdapter(): RepoAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<GithubRepoModel>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    fun fetchRepositories(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val repos = repository.getRepositories(username)
            withContext(Dispatchers.Main) {
                _repositories.value = repos
            }
        }
    }
}