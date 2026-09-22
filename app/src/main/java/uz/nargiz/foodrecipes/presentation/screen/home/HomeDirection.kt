package uz.nargiz.foodrecipes.presentation.screen.home

import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.presentation.screen.category.CategoryScreen
import uz.nargiz.foodrecipes.presentation.screen.detail.DetailScreen
import uz.nargiz.foodrecipes.presentation.screen.saved.SavedScreen
import uz.nargiz.foodrecipes.presentation.screen.search.SearchScreen
import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val navigator: AppNavigator
): HomeContract.Directions {

    override fun navigateToDetail(id: Int) {
        navigator.navigateAdd(DetailScreen(id))
    }

    override fun navigateToSearch(isQuery: Boolean) {
        navigator.navigateAdd(SearchScreen(isQuery))
    }

    override fun navigateToCategory(category: CategoryData) {
        navigator.navigateAdd(CategoryScreen(category))
    }

    override fun navigateToSaved() {
        navigator.navigateAdd(SavedScreen())
    }
}