package com.example.testapp.network

import com.example.testapp.data.GithubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCall {

    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): ArrayList<GithubRepoModel>
}