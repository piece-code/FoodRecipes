package uz.nargiz.foodrecipes.presentation.screen.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.ContainerHost
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.model.RecipeDetail

interface HomeContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        data class Category(val category: CategoryData): Event
        data class Recipe(val recipe: RecipeData): Event
        data class RecipeRecommended(val recipe: RecipeDetail.Data): Event
        data class Search(val isQuery: Boolean = true): Event
        data class Language(val code: String): Event
        object Saved: Event
    }

    sealed interface SideEffect {
        data class Message(val message: String): SideEffect
    }

    data class UIState(
        val isLoading: Boolean = false,
        val categories: List<CategoryData> = emptyList(),
        val recipes: Flow<PagingData<RecipeData>> = emptyFlow(),
        val recommended: RecipeDetail.Data? = null,
        val language: String = ""
    )

    interface Directions {
        fun navigateToDetail(id: Int)
        fun navigateToSearch(isQuery: Boolean)
        fun navigateToCategory(category: CategoryData)
        fun navigateToSaved()
    }
}