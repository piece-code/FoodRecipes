package uz.nargiz.foodrecipes.presentation.screen.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val directions: DetailDirection
): ViewModel(), DetailContract.ViewModel {
    override val container = container<DetailContract.UIState, DetailContract.SideEffect>(
        DetailContract.UIState())

    override fun onEventDispatcher(event: DetailContract.Event) {
    }
}