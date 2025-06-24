package nz.co.jonker.breedview.data

import nz.co.jonker.breedview.api.BreedViewApi

interface BreedViewRepository {
    suspend fun getBreedImages(breedName: String): Result<List<String>>
}

class BreedViewRepositoryImpl(private val breedViewApi: BreedViewApi) : BreedViewRepository {
    override suspend fun getBreedImages(breedName: String): Result<List<String>> {
        return try {
            Result.success(breedViewApi.getBreedImages(breedName).message)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
