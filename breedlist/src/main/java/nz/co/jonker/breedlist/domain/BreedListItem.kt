package nz.co.jonker.breedlist.domain

data class BreedListItem(
    val breedName: String,
    val subBreeds: List<String> = emptyList()
)
