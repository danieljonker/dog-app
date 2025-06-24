package nz.co.jonker.breedview.api

import kotlinx.serialization.Serializable

@Serializable
data class BreedViewResponseDto(val message: List<String>)
