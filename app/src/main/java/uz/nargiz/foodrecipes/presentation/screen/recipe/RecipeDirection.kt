package uz.nargiz.foodrecipes.presentation.screen.recipe

import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class RecipeDirection @Inject constructor(
    private val navigator: AppNavigator
): RecipeContract.Directions {

    override fun navigateToBack() {
        navigator.navigateBack()
    }
}