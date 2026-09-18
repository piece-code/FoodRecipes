package uz.nargiz.foodrecipes.data.source.network.response

import uz.nargiz.foodrecipes.domain.model.RecipeData

data class SearchResponse(
    val items: List<RecipeData>,
    val total: Int,
    val query: String
)