package uz.nargiz.foodrecipes.presentation.screen.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.orbitmvi.orbit.ContainerHost
import uz.nargiz.foodrecipes.data.source.local.Repository
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.model.RecipeDetail

interface HomeContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        object Back: Event
        object Menu: Event
        data class Category(val category: CategoryData): Event
        data class Recipe(val recipe: RecipeData): Event
        data class RecipeRecommended(val recipe: RecipeDetail.Data): Event
        object More: Event
    }

    sealed interface SideEffect

    data class UIState(
        val isLoading: Boolean = false,
        val category: List<CategoryData> = emptyList(),
        val selectedCategory: CategoryData? = null,
        val recipes: Flow<PagingData<RecipeData>> = flow { emit(PagingData.empty()) },
        val recommended: List<RecipeDetail.Data> = listOf(Repository.recipe),
    )

    interface Directions {
        fun navigateToDetail(recipe: RecipeDetail.Data)
    }
}