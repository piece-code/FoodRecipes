package uz.nargiz.foodrecipes.presentation.screen.detail

import org.orbitmvi.orbit.ContainerHost

interface DetailContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        object Back: Event
        object Add: Event
    }

    sealed interface SideEffect

    data class UIState(
        val isLoading: Boolean = false
    )

    interface Directions {}
}