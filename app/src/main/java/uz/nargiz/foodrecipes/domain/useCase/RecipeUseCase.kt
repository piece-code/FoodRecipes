package uz.nargiz.foodrecipes.domain.useCase

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.domain.repository.RecipeRepository
import javax.inject.Inject

sealed interface RecipeUseCase {

    class GetAllRecipes @Inject constructor(
        private val recipeRepository: RecipeRepository
    ) : RecipeUseCase {
        operator fun invoke() : Flow<PagingData<RecipeData>> =
            recipeRepository.getAllRecipes()
    }

    class GetRandomRecipe @Inject constructor(
        private val recipeRepository: RecipeRepository
    ) : RecipeUseCase {
        operator fun invoke() : Flow<Result<RecipeDetail.Data>> = flow {
            emit(recipeRepository.randomRecipe())
        }.catch { emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
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