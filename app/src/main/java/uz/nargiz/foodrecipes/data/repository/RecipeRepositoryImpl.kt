package uz.nargiz.foodrecipes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import uz.nargiz.foodrecipes.data.source.local.AppSharedPref
import uz.nargiz.foodrecipes.data.source.network.api.RecipeApi
import uz.nargiz.foodrecipes.data.source.paging.RecipesPagingSource
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.repository.RecipeRepository
import uz.nargiz.foodrecipes.util.parseError
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val pref: AppSharedPref,
    private val recipeApi: RecipeApi,
    private val gson: Gson
): RecipeRepository {

    override fun getAllRecipes(): Flow<PagingData<RecipeData>> {
        return Pager(
            config = PagingConfig(pageSize = RecipesPagingSource.PAGE_SIZE, enablePlaceholders = false, prefetchDistance = 3),
            pagingSourceFactory = { RecipesPagingSource(recipeApi, pref.lang) }
        ).flow
    }

    override suspend fun randomRecipe(): Result<RecipeDetail.Data> {
        val response = recipeApi.randomRecipe(pref.lang)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!)
        } else {
            Result.failure(gson.parseError(response.errorBody()?.string()))
        }
    }

    override suspend fun getRecipeById(id: Int): Result<RecipeDetail.Data> {
        val response = recipeApi.getRecipeById(id)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!)
        } else {
            Result.failure(gson.parseError(response.errorBody()?.string()))
        }
    }
}