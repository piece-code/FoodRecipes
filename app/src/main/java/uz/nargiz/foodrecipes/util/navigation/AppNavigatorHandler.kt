package uz.nargiz.foodrecipes.util.navigation

import androidx.lifecycle.LiveData

interface AppNavigatorHandler {
    val backstack: LiveData<AppNavigatorParam>
}