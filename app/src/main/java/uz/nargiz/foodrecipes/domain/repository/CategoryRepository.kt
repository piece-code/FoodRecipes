package uz.nargiz.foodrecipes.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipeData

interface CategoryRepository {
    suspend fun getAllCategories(): Result<List<CategoryData>>
    fun getRecipesByKey(key: String): Flow<PagingData<RecipeData>>
}