package uz.nargiz.foodrecipes.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.nargiz.foodrecipes.domain.useCase.SearchUseCase
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipesByQuery: SearchUseCase.Search,
    private val getRecipesByIngredients: SearchUseCase.SearchWithIngredients,
    private val direction: SearchContract.Directions
): ViewModel(), SearchContract.ViewModel {
    override val container = container<SearchContract.UIState, SearchContract.SideEffect>(
        SearchContract.UIState())
    private var searchJob: Job? = null

    override fun onEventDispatcher(event: SearchContract.Event) {
        when(event) {
            is SearchContract.Event.Search -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500.milliseconds)
                    if (event.isQuery)
                        searchWithQuery(event.query)
                    else
                        searchWithIngredients(event.query)
                }
            }
            is SearchContract.Event.Back ->
                direction.navigateToBack()
            is SearchContract.Event.Category ->
                direction.navigateToCategory(event.category)
            is SearchContract.Event.Recipe ->
                direction.navigateToDetail(event.recipe.id)
        }
    }

    private fun searchWithIngredients(query: String) {
        getRecipesByIngredients(query)
            .onStart { intent { reduce { state.copy(isLoading = true) } } }
            .onEach {
                it.onSuccess { recipes ->
                    intent { reduce { state.copy(recipes = recipes) } } }
                it.onFailure { error ->
                    intent { postSideEffect(SearchContract.SideEffect.Message(error.message ?: "Unknown error")) } }
            }
            .onCompletion { intent { reduce { state.copy(isLoading = false) } } }
            .launchIn(viewModelScope)
    }

    private fun searchWithQuery(query: String) {
        getRecipesByQuery(query)
            .onStart { intent { reduce { state.copy(isLoading = true) } } }
            .onEach {
                it.onSuccess { recipes ->
                    intent { reduce { state.copy(recipes = recipes) } } }
                it.onFailure { error ->
                    intent { postSideEffect(SearchContract.SideEffect.Message(error.message ?: "Unknown error")) } }
            }
            .onCompletion { intent { reduce { state.copy(isLoading = false) } } }
            .launchIn(viewModelScope)
    }
}