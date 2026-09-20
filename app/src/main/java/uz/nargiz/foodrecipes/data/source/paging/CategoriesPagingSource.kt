package uz.nargiz.foodrecipes.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.nargiz.foodrecipes.data.source.network.api.CategoryApi
import uz.nargiz.foodrecipes.domain.model.RecipeData

class CategoriesPagingSource(
    private val categoryApi: CategoryApi,
    private val lang: String,
    private val key: String,
) : PagingSource<Int, RecipeData>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val PAGE_SIZE = 10
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, RecipeData> {

        val currentPage = params.key ?: INITIAL_PAGE_INDEX

        return try {
            val response = categoryApi.getRecipesByCategory(
                key = key,
                page = currentPage,
                lang = lang,
                perPage = PAGE_SIZE,
            )

            if (response.isSuccessful) {
                val body = response.body()
                    ?: return LoadResult.Error(
                        Exception("Response body is null")
                    )

                val items = body.items

                val nextKey = if (items.isEmpty()) {
                    null
                } else {
                    currentPage + 1
                }

                LoadResult.Page(
                    data = items,
                    prevKey = if (currentPage == INITIAL_PAGE_INDEX) {
                        null
                    } else {
                        currentPage - 1
                    },
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(
                    Exception(response.message())
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, RecipeData>
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
