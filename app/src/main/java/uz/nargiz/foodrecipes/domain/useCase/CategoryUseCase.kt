package uz.nargiz.foodrecipes.domain.useCase

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.repository.CategoryRepository
import javax.inject.Inject

sealed interface CategoryUseCase {

    class GetAllCategories @Inject constructor(
        private val categoryRepository: CategoryRepository
    ) : CategoryUseCase {
        operator fun invoke() : Flow<Result<List<CategoryData>>> = flow {
            emit(categoryRepository.getAllCategories())
        }.catch { emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    class GetCategoryByKey @Inject constructor(
        private val categoryRepository: CategoryRepository
    ) : CategoryUseCase {
        operator fun invoke(key: String) : Flow<PagingData<RecipeData>> =
            categoryRepository.getRecipesByKey(key)
    }
}