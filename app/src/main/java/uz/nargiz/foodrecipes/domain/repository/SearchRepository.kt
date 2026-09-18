package uz.nargiz.foodrecipes.domain.repository

import uz.nargiz.foodrecipes.domain.model.RecipeData

interface SearchRepository {
    suspend fun search(query: String): Result<List<RecipeData>>
    suspend fun searchWithIngredients(query: String): Result<List<RecipeData>>
}