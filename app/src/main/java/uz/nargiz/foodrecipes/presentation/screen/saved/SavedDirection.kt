package uz.nargiz.foodrecipes.presentation.screen.saved

import uz.nargiz.foodrecipes.presentation.screen.detail.DetailScreen
import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class SavedDirection @Inject constructor(
    private val navigator: AppNavigator
): SavedContract.Directions {

    override fun navigateToDetail(id: Int) {
        navigator.navigateAdd(DetailScreen(id))
    }

    override fun navigateToBack() {
        navigator.navigateBack()
    }
}