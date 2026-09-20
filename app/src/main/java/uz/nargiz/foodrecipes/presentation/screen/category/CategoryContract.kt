package uz.nargiz.foodrecipes.presentation.screen.category

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.ContainerHost
import uz.nargiz.foodrecipes.domain.model.RecipeData

interface CategoryContract {
    interface ViewModel: ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    sealed interface Event {
        data class Recipe(val recipe: RecipeData): Event
        object Back: Event
        data class Init(val key: String): Event
    }

    sealed interface SideEffect {
        data class Message(val message: String): SideEffect
    }

    data class UIState(
        val isLoading: Boolean = false,
        val recipes: Flow<PagingData<RecipeData>> = emptyFlow(),
        val language: String = ""
    )

    interface Directions {
        fun navigateToDetail(id: Int)
        fun navigateToBack()
    }
}