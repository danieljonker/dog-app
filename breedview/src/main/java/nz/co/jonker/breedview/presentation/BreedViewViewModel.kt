package nz.co.jonker.breedview.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nz.co.jonker.breedview.data.BreedViewRepository
import nz.co.jonker.networking.BreedView

class BreedViewViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: BreedViewRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val breedView = savedStateHandle.toRoute<BreedView>()

    private val _screenState =
        MutableStateFlow(BreedViewScreenState(breedName = breedView.breedName))
    val screenState: StateFlow<BreedViewScreenState> = _screenState

    init {
        viewModelScope.launch(ioDispatcher) {
            repository.getBreedImages(breedView.breedName)
                .onSuccess {
                    withContext(mainDispatcher) {
                        _screenState.value =
                            _screenState.value.copy(loading = false, breedImages = it)
                    }
                }
                .onFailure {
                    withContext(mainDispatcher) {
                        _screenState.value = _screenState.value.copy(loading = false, error = true)
                    }
                }
        }
    }
}

data class BreedViewScreenState(
    val breedName: String = "",
    val breedImages: List<String> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false
)
