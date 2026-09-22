package uz.nargiz.foodrecipes.domain.repository

interface SavedRepository {
    fun getAllSavedRecipes(): List<Int>
    fun saveRecipe(recipeId: Int)
    fun deleteRecipe(recipeId: Int)
    fun isSaved(id: Int): Boolean
}