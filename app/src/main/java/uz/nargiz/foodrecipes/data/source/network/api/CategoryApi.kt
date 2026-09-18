package uz.nargiz.foodrecipes.data.source.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipePageData

interface CategoryApi {

    @GET("api/v1/categories/?lang={lang}")
    suspend fun getAllCategories(@Path("lang") lang: String): Response<List<CategoryData>>

    @GET("api/v1/categories/{key}/recipes?lang={lang}&page={page}&per_page={per_page}")
    suspend fun getRecipesByCategory(
        @Path("key") key: String,
        @Path("lang") lang: String,
        @Path("page") page: Int,
        @Path("per_page") perPage: Int
    ): Response<RecipePageData>
}