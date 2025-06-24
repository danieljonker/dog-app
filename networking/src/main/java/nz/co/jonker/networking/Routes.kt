package nz.co.jonker.networking

import kotlinx.serialization.Serializable

@Serializable
object BreedList

@Serializable
class BreedView(val breedName: String)