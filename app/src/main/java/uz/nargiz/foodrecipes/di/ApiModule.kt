package uz.nargiz.foodrecipes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import uz.nargiz.foodrecipes.data.source.network.api.CategoryApi
import uz.nargiz.foodrecipes.data.source.network.api.RecipeApi
import uz.nargiz.foodrecipes.data.source.network.api.SearchApi

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi = retrofit.create<RecipeApi>()

    @Provides
    fun provideCategory(retrofit: Retrofit): CategoryApi = retrofit.create<CategoryApi>()

    @Provides
    fun provideSearch(retrofit: Retrofit): SearchApi = retrofit.create<SearchApi>()
}