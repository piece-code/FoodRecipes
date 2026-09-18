package uz.nargiz.foodrecipes.util.navigation

import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.screen.Screen
import kotlin.reflect.KClass

object AppNavigatorDispatcher: AppNavigator, AppNavigatorHandler {
    override val backstack = MutableLiveData<AppNavigatorParam>()

    private fun navigate(navigate: AppNavigatorParam) {
        backstack.value = navigate
    }

    override fun navigateReplace(screen: Screen) = navigate {
        replace(screen)
    }

    override fun navigateReplaceAll(screen: Screen) = navigate {
        replaceAll(screen)
    }

    override fun navigateAdd(screen: Screen) = navigate {
        push(screen)
    }

    override fun navigateBack() = navigate {
        pop()
    }

    override fun clearBackUntil(screen: KClass<out Screen>) = navigate {
            popUntil { it::class == screen }
        }
}