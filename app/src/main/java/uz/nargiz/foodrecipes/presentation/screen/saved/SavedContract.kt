package uz.nargiz.foodrecipes.presentation.screen.saved

import org.orbitmvi.orbit.ContainerHost
import uz.nargiz.foodrecipes.domain.model.RecipeDetail

interface SavedContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        data class Recipe(val recipe: RecipeDetail.Data): Event
        object Back: Event
    }

    sealed interface SideEffect {
        data class Message(val message: String): SideEffect
    }

    data class UIState(
        val isLoading: Boolean = false,
        val recipes: List<RecipeDetail.Data> = emptyList(),
    )

    interface Directions {
        fun navigateToDetail(id: Int)
        fun navigateToBack()
    }
}