package nz.co.jonker.breedlist.presentation

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.co.jonker.breedlist.domain.BreedListItem
import nz.co.jonker.design.components.TopAppBarComponent

@Composable
fun BreedListScreen(
    viewModel: BreedListViewModel,
    onBreedClickedHandler: (breedName: String) -> Unit,
) {
    val state = viewModel.screenState.collectAsState()

    when {
        state.value.loading -> {}
        state.value.isError -> {}
        state.value.items.isEmpty() -> {}
        else -> BreedListScreenUi(state.value.items, onBreedClickedHandler)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BreedListScreenUi(
    items: List<BreedListItem> = emptyList(),
    onBreedClickedHandler: (breedName: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
        topBar = { TopAppBarComponent("Breeds") }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { 
                Spacer(Modifier.size(8.dp))
            }

            items(items = items) { item ->
                BreedRow(breed = item, onClick = onBreedClickedHandler)
            }
        }
    }
}

@VisibleForTesting
val previewData = listOf(
    BreedListItem("affenpinscher"),
    BreedListItem("australian", listOf("kelpie, shepherd")),
    BreedListItem("beagle"),
    BreedListItem("buhund", listOf("norwegian")),
)

@Preview
@Composable
fun BreedListScreenPreview() {
    BreedListScreenUi(
        items = previewData,
        onBreedClickedHandler = {}
    )
}
