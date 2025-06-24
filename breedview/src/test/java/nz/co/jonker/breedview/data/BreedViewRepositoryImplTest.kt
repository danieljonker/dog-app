package nz.co.jonker.breedview.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import nz.co.jonker.breedview.api.BreedViewApi
import nz.co.jonker.breedview.data.utils.TestData
import org.json.JSONException
import org.junit.Assert.assertEquals
import org.junit.Test


class BreedViewRepositoryImplTest {
    private val breedviewApi: BreedViewApi = mockk()

    private val testSubject = BreedViewRepositoryImpl(breedviewApi)

    @Test
    fun `should fetch and map images from API`() = runTest {
        coEvery { breedviewApi.getBreedImages(any()) } returns TestData.breedResponseDto

        val result = testSubject.getBreedImages(TestData.BREED_NAME)

        assertEquals(Result.success(TestData.breedImages), result)
        coVerify { breedviewApi.getBreedImages(TestData.BREED_NAME) }
    }

    @Test
    fun `should return failure when api call fails`() = runTest {
        val expectedException = JSONException("bad json")

        coEvery { breedviewApi.getBreedImages(any()) } throws expectedException

        val result = testSubject.getBreedImages(TestData.BREED_NAME)

        val expected: Result<List<String>> = Result.failure(expectedException)
        assertEquals(expected, result)
    }
}
