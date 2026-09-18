package uz.nargiz.foodrecipes.presentation.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val directions: HomeDirection
): ViewModel(), HomeContract.ViewModel {
    override val container = container<HomeContract.UIState, HomeContract.SideEffect>(
        HomeContract.UIState())

    override fun onEventDispatcher(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.Back -> {}
            is HomeContract.Event.Menu -> {}
            is HomeContract.Event.More -> {}
            is HomeContract.Event.Recipe ->
                directions.navigateToDetail(event.recipe)
            is HomeContract.Event.Category ->
                intent { reduce { state.copy(selectedCategory = event.category) } }
        }
    }
}
