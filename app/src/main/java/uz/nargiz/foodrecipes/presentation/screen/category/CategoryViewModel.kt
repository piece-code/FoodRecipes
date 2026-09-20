package uz.nargiz.foodrecipes.presentation.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.viewmodel.container
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.useCase.CategoryUseCase
import uz.nargiz.foodrecipes.domain.useCase.LanguageUseCase
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getLanguage: LanguageUseCase.GetLanguageUseCase,
    private val getRecipesByCategoryUseCase: CategoryUseCase.GetCategoryByKey,
    private val directions: CategoryContract.Directions
): ViewModel(), CategoryContract.ViewModel {
    override val container = container<CategoryContract.UIState, CategoryContract.SideEffect>(
        CategoryContract.UIState())
    private var recipes: Flow<PagingData<RecipeData>> = emptyFlow()

    init {
        intent { reduce { state.copy(language = getLanguage()) } }
    }

    override fun onEventDispatcher(event: CategoryContract.Event) {
        when(event) {
            is CategoryContract.Event.Recipe ->
                directions.navigateToDetail(event.recipe.id)
            is CategoryContract.Event.Back ->
                directions.navigateToBack()
            is CategoryContract.Event.Init ->
                getRecipesByCategory(event.key)
        }
    }

    private fun getRecipesByCategory(key: String) {
        recipes = getRecipesByCategoryUseCase(key).cachedIn(viewModelScope)
        intent { reduce { state.copy(recipes = recipes) } }
    }
}