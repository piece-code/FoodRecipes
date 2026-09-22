package uz.nargiz.foodrecipes.presentation.screen.detail

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
class DetailViewModel @Inject constructor(
    private val getRecipeUseCase: RecipeUseCase.GetRecipeById,
    private val directions: DetailContract.Directions,
    private val isSavedUseCase: SavedRecipeUseCase.IsSaved,
    private val saveRecipeUseCase: SavedRecipeUseCase.SaveRecipe,
    private val deleteRecipeUseCase: SavedRecipeUseCase.DeleteRecipe
): ViewModel(), DetailContract.ViewModel {
    override val container = container<DetailContract.UIState, DetailContract.SideEffect>(
        DetailContract.UIState())

    override fun onEventDispatcher(event: DetailContract.Event) {
        when(event) {
            DetailContract.Event.Back ->
                directions.navigateToBack()
            is DetailContract.Event.Load ->
                getRecipe(event.id)
            DetailContract.Event.Play ->
                intent { postSideEffect(DetailContract.SideEffect.OpenVideo(state.recipe!!.videoUrl)) }
            is DetailContract.Event.Save ->
                saveRecipe()
        }
    }

    private fun getRecipe(id: Int) {
        getRecipeUseCase(id)
            .onStart { intent { reduce { state.copy(isLoading = true) } } }
            .onEach {
                it.onSuccess { recipe ->
                    val isSaved = isSavedUseCase(recipe.id)
                    intent { reduce { state.copy(
                        recipe = recipe,
                        isSaved = isSaved) } } }
                it.onFailure { error ->
                    intent { postSideEffect(DetailContract.SideEffect.Message(error.message ?: "Unknown error")) } }
            }
            .onCompletion { intent { reduce { state.copy(isLoading = false) } } }
            .launchIn(viewModelScope)
    }

    private fun saveRecipe() = intent {
        val recipe = state.recipe!!
        if (state.isSaved) deleteRecipeUseCase(recipe.id)
        else saveRecipeUseCase(recipe.id)
        intent { reduce { state.copy(isSaved = !state.isSaved) } }
    }
}