package uz.nargiz.foodrecipes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import uz.nargiz.foodrecipes.data.source.network.api.RecipeApi

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {

    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi = retrofit.create<RecipeApi>()
}