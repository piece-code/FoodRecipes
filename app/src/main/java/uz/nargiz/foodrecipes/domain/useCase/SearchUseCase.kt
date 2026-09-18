package uz.nargiz.foodrecipes.domain.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.repository.SearchRepository
import javax.inject.Inject

sealed interface SearchUseCase {

    class Search @Inject constructor(
        private val searchRepository: SearchRepository
    ) : SearchUseCase {
        operator fun invoke(query: String) : Flow<Result<List<RecipeData>>> = flow {
            emit(searchRepository.search(query))
        }.catch { emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    class SearchWithIngredients @Inject constructor(
        private val searchRepository: SearchRepository
    ) : SearchUseCase {
        operator fun invoke(query: String) : Flow<Result<List<RecipeData>>> = flow {
            emit(searchRepository.searchWithIngredients(query))
        }.catch { emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }
}