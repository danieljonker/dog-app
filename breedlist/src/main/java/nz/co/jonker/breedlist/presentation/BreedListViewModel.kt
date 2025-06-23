package nz.co.jonker.breedlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nz.co.jonker.breedlist.data.BreedsListRepository
import nz.co.jonker.breedlist.domain.BreedListItem

class BreedListViewModel(
    private val repository: BreedsListRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val _screenState: MutableStateFlow<BreedListScreenState> =
        MutableStateFlow(BreedListScreenState())
    val screenState: StateFlow<BreedListScreenState> = _screenState

    init {
        viewModelScope.launch(ioDispatcher) {
            repository.getAllBreeds()
                .onSuccess { result ->
                    withContext(mainDispatcher) {
                        _screenState.value =
                            BreedListScreenState(items = result, loading = false, isError = false)
                    }
                }.onFailure { _ ->
                    withContext(mainDispatcher) {
                        _screenState.value =
                            _screenState.value.copy(isError = true, loading = false)
                    }
                }
        }
    }
}

data class BreedListScreenState(
    val items: List<BreedListItem> = emptyList(),
    val loading: Boolean = true,
    val isError: Boolean = false,
)
