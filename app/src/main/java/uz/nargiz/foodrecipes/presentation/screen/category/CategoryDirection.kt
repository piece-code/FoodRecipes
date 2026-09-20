package uz.nargiz.foodrecipes.presentation.screen.category

import uz.nargiz.foodrecipes.presentation.screen.detail.DetailScreen
import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class CategoryDirection @Inject constructor(
    private val navigator: AppNavigator
): CategoryContract.Directions {

    override fun navigateToDetail(id: Int) {
        navigator.navigateAdd(DetailScreen(id))
    }

    override fun navigateToBack() {
        navigator.navigateBack()
    }
}