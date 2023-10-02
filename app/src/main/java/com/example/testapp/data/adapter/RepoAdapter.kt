package com.example.testapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.GithubRepoModel
import com.example.testapp.databinding.RecyclerLayoutBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoHolder>() {

    var items = ArrayList<GithubRepoModel>()
    lateinit var binding: RecyclerLayoutBinding

    fun setData(data: ArrayList<GithubRepoModel>){
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        binding = RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

  inner  class RepoHolder(items: View): RecyclerView.ViewHolder(items) {

        fun bind(repo: GithubRepoModel){
            binding.repo = repo
            binding.executePendingBindings()
        }
    }
}