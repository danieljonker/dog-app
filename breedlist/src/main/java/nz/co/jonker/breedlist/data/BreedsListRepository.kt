package nz.co.jonker.breedlist.data

import nz.co.jonker.breedlist.api.BreedsListApi
import nz.co.jonker.breedlist.domain.BreedListItem

class BreedsListRepositoryImpl(
    private val breedsListApi: BreedsListApi,
    private val breedsListMapper: BreedsListMapper,
) : BreedsListRepository {

    override suspend fun getAllBreeds(): Result<List<BreedListItem>> {
        try {
            val apiResponse = breedsListApi.getAllBreeds()
            return Result.success(breedsListMapper.map(apiResponse))
        } catch(e: Exception) {
            return Result.failure(e)
        }
    }
}

interface BreedsListRepository {
    suspend fun getAllBreeds(): Result<List<BreedListItem>>
}
