package uz.nargiz.foodrecipes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import uz.nargiz.foodrecipes.data.source.local.AppSharedPref
import uz.nargiz.foodrecipes.data.source.network.api.CategoryApi
import uz.nargiz.foodrecipes.data.source.paging.CategoriesPagingSource
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.repository.CategoryRepository
import uz.nargiz.foodrecipes.util.parseError
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val pref: AppSharedPref,
    private val categoryApi: CategoryApi,
    private val gson: Gson
): CategoryRepository {

    override suspend fun getAllCategories(): Result<List<CategoryData>> {
        val response = categoryApi.getAllCategories(pref.lang)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!)
        } else {
            Result.failure(gson.parseError(response.errorBody()?.string()))
        }
    }

    override fun getRecipesByKey(key: String): Flow<PagingData<RecipeData>> {
        return Pager(
            config = PagingConfig(
                pageSize = CategoriesPagingSource.PAGE_SIZE, enablePlaceholders = false, prefetchDistance = 3),
            pagingSourceFactory = { CategoriesPagingSource(categoryApi, pref.lang, key) }
        ).flow
    }
}