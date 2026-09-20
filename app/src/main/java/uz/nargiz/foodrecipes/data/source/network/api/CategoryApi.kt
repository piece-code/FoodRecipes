package uz.nargiz.foodrecipes.data.source.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipePageData

interface CategoryApi {

    @GET("api/v1/categories/")
    suspend fun getAllCategories(@Query("lang") lang: String): Response<List<CategoryData>>

    @GET("api/v1/categories/{key}/recipes")
    suspend fun getRecipesByCategory(
        @Path("key") key: String,
        @Query("lang") lang: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<RecipePageData>
}