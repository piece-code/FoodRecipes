package uz.nargiz.foodrecipes.presentation.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.viewmodel.container
import uz.nargiz.foodrecipes.domain.useCase.RecipeUseCase
import uz.nargiz.foodrecipes.domain.useCase.SavedRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val direction: SavedContract.Directions,
    private val getSavedUseCase: SavedRecipeUseCase.GetAllSavedRecipes,
    private val getRecipeUseCase: RecipeUseCase.GetRecipeById
): ViewModel(), SavedContract.ViewModel {
    override val container = container<SavedContract.UIState, SavedContract.SideEffect>(
        SavedContract.UIState())

    init {
        getSavedRecipes()
    }

    override fun onEventDispatcher(event: SavedContract.Event) {
        when(event) {
            is SavedContract.Event.Recipe ->
                direction.navigateToDetail(event.recipe.id)
            is SavedContract.Event.Back ->
                direction.navigateToBack()
        }
    }

    private fun getSavedRecipes() {
        val recipesIdList = getSavedUseCase()
        recipesIdList.forEach { recipesId ->
            getRecipeUseCase(recipesId)
                .onStart { intent { reduce { state.copy(isLoading = true) } } }
                .onEach {
                    it.onSuccess { recipe ->
                        intent { reduce { state.copy(recipes = state.recipes + recipe) } } }
                    it.onFailure { error ->
                        intent { postSideEffect(SavedContract.SideEffect.Message(error.message ?: "Unknown error")) } }
                }
                .onCompletion { intent { reduce { state.copy(isLoading = false) } } }
                .launchIn(viewModelScope)
        }
    }
}