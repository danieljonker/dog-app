package nz.co.jonker.breedlist.data

import nz.co.jonker.breedlist.api.BreedsResponseDto
import nz.co.jonker.breedlist.domain.BreedListItem

class BreedsListMapper {

    fun map(
        responseDto: BreedsResponseDto,
    ): List<BreedListItem> {
        return responseDto.message.map { breedEntry ->
            BreedListItem(breedEntry.key, breedEntry.value)
        }
    }
}
