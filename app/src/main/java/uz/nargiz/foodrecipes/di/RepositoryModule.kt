package uz.nargiz.foodrecipes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nargiz.foodrecipes.data.repository.CategoryRepositoryImpl
import uz.nargiz.foodrecipes.data.repository.RecipeRepositoryImpl
import uz.nargiz.foodrecipes.data.repository.SavedRepositoryImpl
import uz.nargiz.foodrecipes.data.repository.SearchRepositoryImpl
import uz.nargiz.foodrecipes.domain.repository.CategoryRepository
import uz.nargiz.foodrecipes.domain.repository.RecipeRepository
import uz.nargiz.foodrecipes.domain.repository.SavedRepository
import uz.nargiz.foodrecipes.domain.repository.SearchRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    fun provideCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun provideSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    fun provideSavedRepository(impl: SavedRepositoryImpl): SavedRepository
}