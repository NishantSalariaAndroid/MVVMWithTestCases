package com.example.testapp.data

import com.example.testapp.network.ApiCall
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository  {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiCall: ApiCall? = retrofit.create(ApiCall::class.java)

    suspend fun getRepositories(username: String): ArrayList<GithubRepoModel>? {
        return apiCall?.getRepositories(username)
    }
}