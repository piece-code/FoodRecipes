package uz.nargiz.foodrecipes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nargiz.foodrecipes.util.navigation.AppNavigator
import uz.nargiz.foodrecipes.util.navigation.AppNavigatorDispatcher
import uz.nargiz.foodrecipes.util.navigation.AppNavigatorHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @[Provides Singleton]
    fun providesAppNavigator(): AppNavigator = AppNavigatorDispatcher

    @[Provides Singleton]
    fun providesAppNavigatorHandler(): AppNavigatorHandler = AppNavigatorDispatcher
}