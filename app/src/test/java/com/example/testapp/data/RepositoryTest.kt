package com.example.testapp.data

import com.example.testapp.network.ApiCall
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryTest {

    @Mock
    private lateinit var apiCall: ApiCall

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = Repository()
        repository.apiCall = apiCall
    }

    @Test
    fun testGetEmptyRepositories() = runBlocking {
        val username = "testuser"
        val expectedRepositories = ArrayList<GithubRepoModel>()
        Mockito.`when`(apiCall.getRepositories(username)).thenReturn(expectedRepositories)
        val result = repository.getRepositories(username)
        Mockito.verify(apiCall).getRepositories(username)
        assert(result == expectedRepositories)
    }

    @Test
    fun testGetExpectedRepositories() = runBlocking {
        val username = "testuser"
        val expectedRepositories = arrayListOf(
            GithubRepoModel(1, "Repo 1", "Description 1", 10),
            GithubRepoModel(2, "Repo 2", "Description 2", 20)
        )
        Mockito.`when`(apiCall.getRepositories(username)).thenReturn(expectedRepositories)
        val result = repository.getRepositories(username)
        Mockito.verify(apiCall).getRepositories(username)
        assert(2 == result!!.size)
    }
}