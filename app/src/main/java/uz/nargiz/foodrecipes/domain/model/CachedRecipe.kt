package uz.nargiz.foodrecipes.domain.model

data class CachedRecipe(
    val recipe: RecipeDetail.Data,
    val savedDate: String
)
