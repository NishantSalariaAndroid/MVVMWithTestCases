package com.example.testapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.viewmodels.RepoViewModel
import com.example.testapp.viewmodels.RepoViewModelFactory

class MainActivity : AppCompatActivity(){
    private lateinit var viewModel: RepoViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, RepoViewModelFactory())[RepoViewModel::class.java]
       viewModel.fetchRepositories("abdulqadirtr")

        binding.viewModel = viewModel
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.repositories.observe(this, Observer { repositories ->
            viewModel.setAdapterData(repositories)
        })


    }
}

