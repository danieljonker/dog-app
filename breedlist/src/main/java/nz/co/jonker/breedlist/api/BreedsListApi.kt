package nz.co.jonker.breedlist.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import nz.co.jonker.networking.BASE_URL

private const val URI = "breeds/list/all"

class BreedsListKtorApi(private val ktorClient: HttpClient): BreedsListApi {
    override suspend fun getAllBreeds(): BreedsResponseDto {
        return ktorClient.get(BASE_URL + URI).body()
    }
}

interface BreedsListApi {
    suspend fun getAllBreeds(): BreedsResponseDto
}
