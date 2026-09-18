package uz.nargiz.foodrecipes.presentation.screen.recipe

import org.orbitmvi.orbit.ContainerHost

interface RecipeContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        object Back: Event
        object Play: Event
    }

    sealed interface SideEffect

    data class UIState(
        val isLoading: Boolean = false
    )

    interface Directions {
        fun navigateToBack()
    }
}