package uz.nargiz.foodrecipes.data.source.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.nargiz.foodrecipes.data.source.network.response.SearchResponse

interface SearchApi {

    @GET("api/v1/search/")
    suspend fun search(
        @Query("q") query: String,
        @Query("lang") lang: String,
        @Query("limit") limit: Int
    ): Response<SearchResponse>

    @GET("api/v1/search/ingredients")
    suspend fun searchWithIngredients(
        @Query("q") query: String,
        @Query("lang") lang: String,
        @Query("limit") limit: Int
    ): Response<SearchResponse>
}
