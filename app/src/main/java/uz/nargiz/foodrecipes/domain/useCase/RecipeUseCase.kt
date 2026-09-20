package uz.nargiz.foodrecipes.domain.useCase

import androidx.paging.PagingData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import uz.nargiz.foodrecipes.data.source.local.AppSharedPref
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.domain.repository.RecipeRepository
import uz.nargiz.foodrecipes.util.toPreferenceString
import uz.nargiz.foodrecipes.util.toRecipeDetail
import java.time.LocalDate
import javax.inject.Inject

sealed interface RecipeUseCase {

    class GetAllRecipes @Inject constructor(
        private val recipeRepository: RecipeRepository
    ) : RecipeUseCase {
        operator fun invoke() : Flow<PagingData<RecipeData>> =
            recipeRepository.getAllRecipes()
    }

    class GetRandomRecipe @Inject constructor(
        private val gson: Gson,
        private val pref: AppSharedPref,
        private val recipeRepository: RecipeRepository
    ) : RecipeUseCase {
        operator fun invoke() : Flow<Result<RecipeDetail.Data>> = flow {
            val cachedRecipe = pref.recommended.toRecipeDetail(gson)
            if (cachedRecipe != null && cachedRecipe.savedDate == LocalDate.now().toString()) {
                emit(Result.success(cachedRecipe.recipe))
            } else {
                emit(recipeRepository.randomRecipe())
            }
        }
            .onEach { it.onSuccess { recipe -> pref.recommended = recipe.toPreferenceString(gson) } }
            .catch { emit(Result.failure(it)) }
            .flowOn(Dispatchers.IO)
    }

    class GetRecipeById @Inject constructor(
        private val recipeRepository: RecipeRepository
    ) : RecipeUseCase {
        operator fun invoke(id: Int) : Flow<Result<RecipeDetail.Data>> = flow {
            emit(recipeRepository.getRecipeById(id))
        }.catch { emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

}