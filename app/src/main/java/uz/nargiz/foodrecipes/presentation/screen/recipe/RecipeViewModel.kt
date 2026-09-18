package uz.nargiz.foodrecipes.presentation.screen.recipe

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val direction: RecipeDirection
): ViewModel(), RecipeContract.ViewModel {
    override val container = container<RecipeContract.UIState, RecipeContract.SideEffect>(
        RecipeContract.UIState())

    override fun onEventDispatcher(event: RecipeContract.Event) {
    }
}