package nz.co.jonker.breedlist.data

import nz.co.jonker.breedlist.api.BreedsResponseDto
import nz.co.jonker.breedlist.domain.BreedListItem
import nz.co.jonker.breedlist.utils.TestData
import org.junit.Assert.*
import org.junit.Test

class BreedsListMapperTest {

    private val testSubject = BreedsListMapper()

    @Test
    fun `should map full response from BreedsResponseDto to list`() {
        val result = testSubject.map(TestData.breedsResponseDto)

        assertEquals(TestData.mappedResponse, result)
    }

    @Test
    fun `should map empty map to empty list`() {
        val result = testSubject.map(BreedsResponseDto(message = emptyMap()))

        assertEquals(emptyList<BreedListItem>(), result)
    }
}
