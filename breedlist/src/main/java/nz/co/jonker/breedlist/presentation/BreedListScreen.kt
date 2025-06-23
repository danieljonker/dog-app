@file:OptIn(ExperimentalMaterial3Api::class)

package nz.co.jonker.breedlist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.co.jonker.breedlist.domain.BreedListItem
import nz.co.jonker.design.components.TopAppBarComponent
import nz.co.jonker.design.theme.DogsAppTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BreedListScreen(
    viewModel: BreedListViewModel = koinViewModel()
) {

    val state = viewModel.screenState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
        topBar = { TopAppBarComponent("Breeds") }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = state.value.items) { item ->
                BreedRow(breed = item)
            }
        }
    }
}

@Composable
fun BreedRow(
    modifier: Modifier = Modifier,
    breed: BreedListItem,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = breed.breedName.capitalize(Locale.current)
    )
}

@Preview(showBackground = false)
@Composable
fun BreedRowPreview() {
    DogsAppTheme {
        BreedRow(breed = BreedListItem(breedName = "labrador"))
    }
}

