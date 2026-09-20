package uz.nargiz.foodrecipes.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.google.gson.Gson
import uz.nargiz.foodrecipes.domain.model.CachedRecipe
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import java.time.LocalDate

fun String.getAvatar(): String =
    this[0].toString().uppercase()

fun RecipeDetail.Data.toPreferenceString(gson: Gson): String {
    val cachedRecipe = CachedRecipe(
        recipe = this,
        savedDate = LocalDate.now().toString()
    )
    return gson.toJson(cachedRecipe)
}

fun String.toRecipeDetail(gson: Gson): CachedRecipe? {
    if (this.isEmpty()) return null
    return gson.fromJson(this, CachedRecipe::class.java)
}