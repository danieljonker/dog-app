package nz.co.jonker.breedlist.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import nz.co.jonker.breedlist.api.BreedsListApi
import nz.co.jonker.breedlist.domain.BreedListItem
import nz.co.jonker.breedlist.utils.TestData
import org.json.JSONException
import org.junit.Test

class BreedsListRepositoryImplTest {

    private val breedsListApi: BreedsListApi = mockk()
    private val breedsListMapper: BreedsListMapper = mockk()

    private val testSubject = BreedsListRepositoryImpl(breedsListApi, breedsListMapper)

    @Test
    fun `should fetch and map breeds from API`() = runTest {
        coEvery { breedsListApi.getAllBreeds() } returns TestData.breedsResponseDto
        every { breedsListMapper.map(any()) } returns TestData.mappedResponse

        val result = testSubject.getAllBreeds()

        assertEquals(Result.success(TestData.mappedResponse), result)
        coVerify { breedsListApi.getAllBreeds() }
        verify { breedsListMapper.map(TestData.breedsResponseDto) }
    }

    @Test
    fun `should return failure when api call fails`() = runTest {
        val expectedException = JSONException("bad json")

        coEvery { breedsListApi.getAllBreeds() } throws expectedException

        val result = testSubject.getAllBreeds()

        val expected: Result<List<BreedListItem>> = Result.failure(expectedException)
        assertEquals(expected, result)
    }
}
