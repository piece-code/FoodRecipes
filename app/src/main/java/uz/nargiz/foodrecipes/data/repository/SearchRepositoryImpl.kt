package uz.nargiz.foodrecipes.data.repository

import com.google.gson.Gson
import uz.nargiz.foodrecipes.data.source.local.AppSharedPref
import uz.nargiz.foodrecipes.data.source.network.api.SearchApi
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.repository.SearchRepository
import uz.nargiz.foodrecipes.util.parseError
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val pref: AppSharedPref,
    private val searchApi: SearchApi,
    private val gson: Gson
): SearchRepository {

    override suspend fun search(query: String): Result<List<RecipeData>> {
        val response = searchApi.search(query, pref.lang, 10)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.items)
        } else {
            Result.failure(gson.parseError(response.errorBody()?.string()))
        }
    }

    override suspend fun searchWithIngredients(query: String): Result<List<RecipeData>> {
        val response = searchApi.searchWithIngredients(query, pref.lang, 10)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.items)
        } else {
            Result.failure(gson.parseError(response.errorBody()?.string()))
        }
    }
}