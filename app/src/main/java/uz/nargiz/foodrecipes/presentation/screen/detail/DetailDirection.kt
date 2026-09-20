package uz.nargiz.foodrecipes.presentation.screen.detail

import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class DetailDirection @Inject constructor(
    private val navigator: AppNavigator
): DetailContract.Directions {

    override fun navigateToBack() {
        navigator.navigateBack()
    }
}