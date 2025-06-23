package nz.co.jonker.breedlist.utils

import nz.co.jonker.breedlist.api.BreedsResponseDto
import nz.co.jonker.breedlist.domain.BreedListItem

object TestData {
    private const val BREED_ONE = "labrador"
    private const val BREED_TWO = "australian"
    private const val SUB_BREED_ONE = "kelpie"
    private const val SUB_BREED_TWO = "shepherd"
    private const val BREED_THREE = "samoyed"

    val breedsResponseDto = BreedsResponseDto(
        message = mapOf(
            BREED_ONE to emptyList(),
            BREED_TWO to listOf(SUB_BREED_ONE, SUB_BREED_TWO),
            BREED_THREE to emptyList(),
        )
    )

    val mappedResponse = listOf(
        BreedListItem(BREED_ONE),
        BreedListItem(BREED_TWO, listOf(SUB_BREED_ONE, SUB_BREED_TWO)),
        BreedListItem(BREED_THREE)
    )
}
