package uz.nargiz.foodrecipes.presentation.screen.search

import org.orbitmvi.orbit.ContainerHost
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipeData

interface SearchContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        data class Search(
            val isQuery: Boolean,
            val query: String): Event
        object Back: Event
        data class Category(val category: CategoryData): Event
        data class Recipe(val recipe: RecipeData): Event
    }

    sealed interface SideEffect {
        data class Message(val message: String): SideEffect
    }

    data class UIState(
        val isLoading: Boolean = false,
        val recipes: List<RecipeData> = emptyList(),
    )

    interface Directions {
        fun navigateToBack()
        fun navigateToCategory(category: CategoryData)
        fun navigateToDetail(id: Int)
    }
}