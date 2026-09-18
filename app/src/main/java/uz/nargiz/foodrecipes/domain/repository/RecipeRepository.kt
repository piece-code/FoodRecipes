package uz.nargiz.foodrecipes.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.domain.model.RecipeData

interface RecipeRepository {
    fun getAllRecipes(): Flow<PagingData<RecipeData>>
    suspend fun randomRecipe(): Result<RecipeDetail.Data>
    suspend fun getRecipeById(id: Int): Result<RecipeDetail.Data>
}