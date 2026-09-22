package uz.nargiz.foodrecipes.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.nargiz.foodrecipes.data.source.local.AppSharedPref
import uz.nargiz.foodrecipes.domain.repository.SavedRepository
import javax.inject.Inject

class SavedRepositoryImpl @Inject constructor(
    private val pref: AppSharedPref,
    private val gson: Gson
): SavedRepository {

    override fun getAllSavedRecipes(): List<Int> {
        val json = pref.saved
        val type = object : TypeToken<List<Int>>() {}.type
        return if (json.isEmpty()) emptyList()
            else gson.fromJson(json, type)
    }

    override fun saveRecipe(recipeId: Int) {
        val json = pref.saved
        val type = object : TypeToken<List<Int>>() {}.type
        val list: MutableList<Int> = if (json.isEmpty()) mutableListOf()
        else gson.fromJson(json, type)
        list.add(recipeId)
        pref.saved = gson.toJson(list)
    }

    override fun deleteRecipe(recipeId: Int) {
        val json = pref.saved
        val type = object : TypeToken<List<Int>>() {}.type
        val list: MutableList<Int> = if (json.isEmpty()) mutableListOf()
        else gson.fromJson(json, type)
        list.remove(element = recipeId)
        pref.saved = gson.toJson(list)
    }

    override fun isSaved(id: Int): Boolean {
        val list = getAllSavedRecipes()
        return list.contains(id)
    }
}