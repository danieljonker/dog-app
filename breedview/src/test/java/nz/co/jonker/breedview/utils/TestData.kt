package nz.co.jonker.breedview.data.utils

import nz.co.jonker.breedview.api.BreedViewResponseDto
import nz.co.jonker.networking.BreedView

object TestData {
    const val BREED_NAME = "labrador"

    val breedView = BreedView(BREED_NAME)

    val breedImages = listOf(
        "url/to/image1",
        "url/to/image2",
        "url/to/image3",
        "url/to/image4",
    )

    val breedResponseDto = BreedViewResponseDto(message = breedImages)
}