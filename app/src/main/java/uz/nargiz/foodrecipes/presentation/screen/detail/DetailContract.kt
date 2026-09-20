package uz.nargiz.foodrecipes.presentation.screen.detail

import org.orbitmvi.orbit.ContainerHost
import uz.nargiz.foodrecipes.domain.model.RecipeDetail

interface DetailContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        object Back: Event
        data class Load(val id: Int): Event
        object Play: Event
    }

    sealed interface SideEffect {
        data class OpenVideo(val url: String): SideEffect
        data class Message(val message: String): SideEffect
    }

    data class UIState(
        val isLoading: Boolean = false,
        val recipe: RecipeDetail.Data? = null
    )

    interface Directions {
        fun navigateToBack()
    }
}