package uz.nargiz.foodrecipes.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.viewmodel.container
import uz.nargiz.foodrecipes.domain.useCase.CategoryUseCase
import uz.nargiz.foodrecipes.domain.useCase.LanguageUseCase
import uz.nargiz.foodrecipes.domain.useCase.RecipeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: RecipeUseCase.GetAllRecipes,
    private val getCategoriesUseCase: CategoryUseCase.GetAllCategories,
    private val getRecipeUseCase: RecipeUseCase.GetRandomRecipe,
    private val getLanguage: LanguageUseCase.GetLanguageUseCase,
    private val setLanguage: LanguageUseCase.SetLanguageUseCase,
    private val directions: HomeContract.Directions
): ViewModel(), HomeContract.ViewModel {
    override val container = container<HomeContract.UIState, HomeContract.SideEffect>(
        HomeContract.UIState())
    private val recipes = getRecipesUseCase().cachedIn(viewModelScope)

    init {
        intent { reduce { state.copy(language = getLanguage()) } }
    }

    init {
        getAllRecipes()
        getCategories()
        getRandomRecipe()
    }

    override fun onEventDispatcher(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.Recipe ->
                directions.navigateToDetail(event.recipe.id)
            is HomeContract.Event.Category ->
                directions.navigateToCategory(event.category)
            is HomeContract.Event.RecipeRecommended ->
                directions.navigateToDetail(event.recipe.id)
            is HomeContract.Event.Search ->
                directions.navigateToSearch(event.isQuery)
            is HomeContract.Event.Language ->
                changeLanguage(event.code)
        }
    }

    fun changeLanguage(code: String) = intent {
        val lang = if (code == "uz") "ru" else "uz"
        setLanguage(lang)
        intent { reduce { state.copy(language = lang) } }
    }

    private fun getAllRecipes() {
        intent { reduce { state.copy(recipes = recipes) } }
    }

    private fun getCategories() {
        getCategoriesUseCase()
            .onEach {
                it.onSuccess { categories ->
                    intent { reduce { state.copy(categories = categories) } } }
                it.onFailure { error ->
                    intent { postSideEffect(HomeContract.SideEffect.Message(error.message ?: "Unknown error")) } }
            }
            .launchIn(viewModelScope)
    }

    private fun getRandomRecipe() {
        getRecipeUseCase()
            .onEach {
                it.onSuccess { recipe ->
                    intent { reduce { state.copy(recommended = recipe) } } }
                it.onFailure { error ->
                    intent { postSideEffect(HomeContract.SideEffect.Message(error.message ?: "Unknown error")) } }
            }
            .launchIn(viewModelScope)
    }
}
