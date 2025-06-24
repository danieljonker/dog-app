package nz.co.jonker.breedlist.presentation

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.co.jonker.breedlist.domain.BreedListItem
import nz.co.jonker.design.theme.DogsAppTheme
import nz.co.jonker.design.theme.Typography

@Composable
fun BreedRow(
    modifier: Modifier = Modifier,
    breed: BreedListItem,
    onClick: (String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(breed.breedName) }
    ) {

        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Text(
                text = breed.breedName.capitalize(Locale.current),
                style = Typography.titleLarge
            )

            FlowRow {
                breed.subBreeds.forEach {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        text = it, style = Typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BreedRowWithSomeSubbreedsPreview() {
    DogsAppTheme {
        BreedRow(
            breed = BreedListItem(
                breedName = "australian",
                subBreeds = listOf("kelpie", "shephard")
            ), onClick = {})
    }
}

@Preview(showBackground = false)
@Composable
fun BreedRowWithoutSubbreedsPreview() {
    DogsAppTheme {
        BreedRow(
            breed = BreedListItem(breedName = "australian"), onClick = {})
    }
}

@VisibleForTesting
val terrierSubBreeds = listOf(
    "american",
    "australian",
    "bedlington",
    "border",
    "cairn",
    "dandie",
    "fox",
    "irish",
    "kerryblue",
    "jakeland",
    "norfolk"
)

@Preview(showBackground = false)
@Composable
fun BreedRowWithLotsOfSubbreedsPreview() {
    DogsAppTheme {
        BreedRow(
            breed =
                BreedListItem(
                    breedName = "terrier",
                    subBreeds = terrierSubBreeds
                ),
            onClick = {})
    }
}
