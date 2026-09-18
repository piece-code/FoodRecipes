package uz.nargiz.foodrecipes.util.navigation

import cafe.adriel.voyager.core.screen.Screen
import kotlin.reflect.KClass

interface AppNavigator {
    fun navigateReplace(screen: Screen)
    fun navigateReplaceAll(screen: Screen)
    fun navigateAdd(screen: Screen)
    fun navigateBack()
    fun clearBackUntil(screen: KClass<out Screen>)
}