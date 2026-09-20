package uz.nargiz.foodrecipes.presentation.screen.search

import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.presentation.screen.category.CategoryScreen
import uz.nargiz.foodrecipes.presentation.screen.detail.DetailScreen
import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class SearchDirection @Inject constructor(
    private val navigator: AppNavigator
): SearchContract.Directions {

    override fun navigateToBack() {
        navigator.navigateBack()
    }

    override fun navigateToCategory(category: CategoryData) {
        navigator.navigateAdd(CategoryScreen(category))
    }

    override fun navigateToDetail(id: Int) {
        navigator.navigateAdd(DetailScreen(id))
    }
}