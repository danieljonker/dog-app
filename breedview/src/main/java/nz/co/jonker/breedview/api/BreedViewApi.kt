package nz.co.jonker.breedview.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import nz.co.jonker.networking.BASE_URL

private const val PATH_SEGMENT_ONE = "breed"
private const val PATH_SEGMENT_THREE = "images/random/10"

class BreedViewKtorApi(private val ktorClient: HttpClient) : BreedViewApi {
    override suspend fun getBreedImages(breedName: String): BreedViewResponseDto {
        return ktorClient.get(BASE_URL) {
            url {
                appendPathSegments(PATH_SEGMENT_ONE, breedName, PATH_SEGMENT_THREE)
            }
        }.body()
    }
}

interface BreedViewApi {
    suspend fun getBreedImages(breedName: String): BreedViewResponseDto
}