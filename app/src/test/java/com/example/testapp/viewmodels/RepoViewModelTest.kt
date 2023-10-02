package com.example.testapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testapp.data.GithubRepoModel
import com.example.testapp.data.Repository

import com.example.testapp.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepoViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    private lateinit var viewModel: RepoViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = RepoViewModel(mockRepository)
    }

    @Test
    fun testFetchRepositories() = runTest {
        val mockRepositories = arrayListOf(

            GithubRepoModel(1, "Repo 1", "Description 1", 10),
            GithubRepoModel(2, "Repo 2", "Description 2", 20)
        )

        Mockito.`when`(mockRepository.getRepositories("abdulqadirtr")).thenReturn(mockRepositories)

        viewModel.fetchRepositories("abdulqadirtr")

        testDispatcher.scheduler.advanceUntilIdle()
        val repositories = viewModel.repositories.getOrAwaitValue()

        assertEquals(mockRepositories, repositories)
    }

    @After
    fun close() {
        Dispatchers.shutdown()
    }
}

