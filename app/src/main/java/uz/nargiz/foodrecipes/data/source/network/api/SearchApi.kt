package uz.nargiz.foodrecipes.data.source.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.nargiz.foodrecipes.data.source.network.response.SearchResponse

interface SearchApi {

    @GET("api/v1/search/?q={q}&lang={lang}&limit={limit}")
    suspend fun search(
        @Path("q") query: String,
        @Path("lang") lang: String,
        @Path("limit") limit: Int
    ): Response<SearchResponse>

    @GET("api/v1/search/ingredients?q={q}&lang={lang}&limit={limit}")
    suspend fun searchWithIngredients(
        @Path("q") query: String,
        @Path("lang") lang: String,
        @Path("limit") limit: Int
    ): Response<SearchResponse>
}
