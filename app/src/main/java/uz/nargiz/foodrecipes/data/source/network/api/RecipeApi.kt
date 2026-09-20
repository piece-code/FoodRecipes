package uz.nargiz.foodrecipes.data.source.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.domain.model.RecipePageData

interface RecipeApi {

    @GET("api/v1/recipes/")
    suspend fun getAllRecipes(
        @Query("lang") lang: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<RecipePageData>

    @GET("api/v1/recipes/random")
    suspend fun randomRecipe(@Query("lang") lang: String): Response<RecipeDetail.Data>

    @GET("api/v1/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Response<RecipeDetail.Data>
}