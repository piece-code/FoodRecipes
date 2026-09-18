package uz.nargiz.foodrecipes.domain.model

import com.google.gson.annotations.SerializedName

data class RecipePageData(
    val items: List<RecipeData>,
    val total: Int,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
