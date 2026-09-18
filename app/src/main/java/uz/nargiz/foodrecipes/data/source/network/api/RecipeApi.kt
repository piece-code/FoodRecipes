package uz.nargiz.foodrecipes.data.source.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.domain.model.RecipePageData

interface RecipeApi {

    @GET("api/v1/recipes/?lang={lang}&page={page}&per_page={per_page}")
    suspend fun getAllRecipes(
        @Path("lang") lang: String,
        @Path("page") page: Int,
        @Path("per_page") perPage: Int
    ): Response<RecipePageData>

    @GET("api/v1/recipes/random?lang={lang}")
    suspend fun randomRecipe(@Path("lang") lang: String): Response<RecipeDetail.Data>

    @GET("api/v1/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Response<RecipeDetail.Data>
}