package com.example.clean.domain.usecase

import com.example.clean.data.repository.user.UserRepository
import com.example.clean.domain.model.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Unit tests for SearchRepositoryUseCase
 * Tests business logic for user search functionality
 */
class SearchRepositoryUseCaseTest {

    private val testUsers = listOf(
        User(
            id = 1,
            login = "dinkar1708",
            type = "User",
            avatarUrl = "https://example.com/avatar1.png",
            score = 98.5
        ),
        User(
            id = 2,
            login = "dinkar2023",
            type = "User",
            avatarUrl = "https://example.com/avatar2.png",
            score = 85.0
        )
    )

    private val mockRepository: UserRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var searchRepositoryUseCase: SearchRepositoryUseCase

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        searchRepositoryUseCase = SearchRepositoryUseCase(mockRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchUsers should return users from repository`() = runTest {
        // Given - Repository returns users
        val query = "dinkar"
        coEvery { mockRepository.searchUsers(query) } returns flowOf(testUsers)

        // When - Use case is invoked
        val result = searchRepositoryUseCase.searchUsers(query).first()

        // Then - Should return users from repository
        assertNotNull(result)
        assertEquals(2, result.size)
        assertEquals("dinkar1708", result[0].login)
        assertEquals("dinkar2023", result[1].login)
        coVerify { mockRepository.searchUsers(query) }
    }

    @Test
    fun `searchUsers with empty query should return empty list`() = runTest {
        // Given - Repository returns empty list
        coEvery { mockRepository.searchUsers("") } returns flowOf(emptyList())

        // When - Use case is invoked with empty query
        val result = searchRepositoryUseCase.searchUsers("").first()

        // Then - Should return empty list
        assertNotNull(result)
        assertTrue(result.isEmpty())
        coVerify { mockRepository.searchUsers("") }
    }

    @Test
    fun `searchUsers should propagate repository errors`() = runTest {
        // Given - Repository throws exception
        val query = "test"
        val errorMessage = "Database error"
        coEvery { mockRepository.searchUsers(query) } throws Exception(errorMessage)

        // When/Then - Use case should propagate exception
        try {
            searchRepositoryUseCase.searchUsers(query).first()
            assertTrue(false, "Should have thrown exception")
        } catch (e: Exception) {
            assertEquals(errorMessage, e.message)
            coVerify { mockRepository.searchUsers(query) }
        }
    }

    @Test
    fun `searchUsers should handle special characters in query`() = runTest {
        // Given - Query with special characters
        val query = "test@#$%"
        coEvery { mockRepository.searchUsers(query) } returns flowOf(emptyList())

        // When - Use case is invoked
        val result = searchRepositoryUseCase.searchUsers(query).first()

        // Then - Should handle gracefully
        assertNotNull(result)
        assertTrue(result.isEmpty())
        coVerify { mockRepository.searchUsers(query) }
    }

    @Test
    fun `searchUsers should filter and return only matching users`() = runTest {
        // Given - Repository returns filtered users
        val query = "1708"
        val filteredUsers = listOf(testUsers[0]) // Only first user matches
        coEvery { mockRepository.searchUsers(query) } returns flowOf(filteredUsers)

        // When - Use case is invoked
        val result = searchRepositoryUseCase.searchUsers(query).first()

        // Then - Should return only matching users
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("dinkar1708", result[0].login)
        coVerify { mockRepository.searchUsers(query) }
    }
}