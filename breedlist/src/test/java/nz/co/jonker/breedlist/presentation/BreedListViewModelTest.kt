package nz.co.jonker.breedlist.presentation

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import nz.co.jonker.breedlist.data.BreedsListRepository
import nz.co.jonker.breedlist.utils.TestData
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedListViewModelTest {

    private val standardTestDispatcher = StandardTestDispatcher()
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()
    private val repository: BreedsListRepository = mockk()

    private lateinit var testSubject: BreedListViewModel

    @Test
    fun `should have loading state on initialisation`() = runTest {
        testSubject =
            BreedListViewModel(repository, standardTestDispatcher, standardTestDispatcher)

        val isLoading = testSubject.screenState.value.loading

        assertEquals(true, isLoading)
    }

    @Test
    fun `should return error state when the repo returns a failure`() = runTest {
        coEvery { repository.getAllBreeds() } returns Result.failure(IllegalStateException())
        testSubject =
            BreedListViewModel(repository, unconfinedTestDispatcher, unconfinedTestDispatcher)

        val state = testSubject.screenState.value

        assertEquals(true, state.isError)
        assertEquals(false, state.loading)
    }

    @Test
    fun `should return success state when repo returns success`() = runTest {
        coEvery { repository.getAllBreeds() } returns Result.success(TestData.mappedResponse)
        testSubject =
            BreedListViewModel(repository, unconfinedTestDispatcher, unconfinedTestDispatcher)

        val state = testSubject.screenState.value

        assertEquals(TestData.mappedResponse, state.items)
        assertEquals(false, state.isError)
        assertEquals(false, state.loading)
    }
}
