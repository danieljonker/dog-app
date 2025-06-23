package nz.co.jonker.breedlist.api

import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponseDto(
    val message: Map<String, List<String>>,
)
