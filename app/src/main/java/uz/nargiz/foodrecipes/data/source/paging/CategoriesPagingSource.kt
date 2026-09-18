package uz.nargiz.foodrecipes.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.nargiz.foodrecipes.data.source.network.api.CategoryApi
import uz.nargiz.foodrecipes.domain.model.RecipeData

class CategoriesPagingSource(
    private val categoryApi: CategoryApi,
    private val lang: String,
    private val key: String,
): PagingSource<Int, RecipeData>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val PAGE_SIZE = 10
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeData> {
        val currentPage = params.key ?: INITIAL_PAGE_INDEX
        val response = categoryApi.getRecipesByCategory(
            key = key,
            page = currentPage,
            lang = lang,
            perPage = PAGE_SIZE,
        )
        return if (response.isSuccessful) {
            val nextKey = if (response.body() == null) null else currentPage + 1
            LoadResult.Page(
                data = response.body()!!.items,
                prevKey = if (currentPage == INITIAL_PAGE_INDEX) null else currentPage - 1,
                nextKey = nextKey
            )
        }else{
            val exception = Exception(response.message())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecipeData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}