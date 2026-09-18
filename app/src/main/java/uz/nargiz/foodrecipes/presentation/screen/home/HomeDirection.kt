package uz.nargiz.foodrecipes.presentation.screen.home

import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.presentation.screen.recipe.RecipeScreen
import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val navigator: AppNavigator
): HomeContract.Directions {

    override fun navigateToDetail(recipe: RecipeDetail.Data) {
        navigator.navigateAdd(RecipeScreen(recipe))
    }
}