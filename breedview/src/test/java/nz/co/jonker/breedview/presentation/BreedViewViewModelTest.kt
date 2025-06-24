package nz.co.jonker.breedview.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import nz.co.jonker.breedview.data.BreedViewRepository
import nz.co.jonker.breedview.data.utils.TestData
import nz.co.jonker.networking.BreedView
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedViewViewModelTest {

    private val standardTestDispatcher = StandardTestDispatcher()
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()
    private val savedStateHandle: SavedStateHandle = mockk()

    private val repository: BreedViewRepository = mockk()

    private lateinit var testSubject: BreedViewViewModel

    @Before
    fun setup() {
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { savedStateHandle.toRoute<BreedView>() } returns TestData.breedView
    }

    @After
    fun tearDown() {
        unmockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    @Test
    fun `should have loading state on initialisation`() = runTest {
        testSubject =
            BreedViewViewModel(savedStateHandle, repository, standardTestDispatcher, standardTestDispatcher)

        val isLoading = testSubject.screenState.value.loading

        assertEquals(true, isLoading)
    }

    @Test
    fun `should return error state when the repo returns a failure`() = runTest {
        coEvery { repository.getBreedImages(any()) } returns Result.failure(IllegalStateException())
        testSubject =
            BreedViewViewModel(savedStateHandle, repository, unconfinedTestDispatcher, unconfinedTestDispatcher)

        val state = testSubject.screenState.value

        assertEquals(TestData.BREED_NAME, state.breedName)
        assertEquals(true, state.error)
        assertEquals(false, state.loading)
    }

    @Test
    fun `should return success state when repo returns success`() = runTest {
        coEvery { repository.getBreedImages(any()) } returns Result.success(TestData.breedImages)
        testSubject =
            BreedViewViewModel(savedStateHandle, repository, unconfinedTestDispatcher, unconfinedTestDispatcher)

        val state = testSubject.screenState.value

        assertEquals(TestData.BREED_NAME, state.breedName)
        assertEquals(TestData.breedImages, state.breedImages)
        assertEquals(false, state.error)
        assertEquals(false, state.loading)
    }
}
