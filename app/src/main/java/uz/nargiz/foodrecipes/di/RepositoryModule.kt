package uz.nargiz.foodrecipes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nargiz.foodrecipes.data.repository.CategoryRepositoryImpl
import uz.nargiz.foodrecipes.data.repository.RecipeRepositoryImpl
import uz.nargiz.foodrecipes.data.repository.SearchRepositoryImpl
import uz.nargiz.foodrecipes.domain.repository.CategoryRepository
import uz.nargiz.foodrecipes.domain.repository.RecipeRepository
import uz.nargiz.foodrecipes.domain.repository.SearchRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Provides
    fun provideRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Provides
    fun provideCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Provides
    fun provideSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}