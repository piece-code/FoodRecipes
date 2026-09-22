package uz.nargiz.foodrecipes.domain.useCase

import uz.nargiz.foodrecipes.domain.repository.SavedRepository
import javax.inject.Inject

sealed interface SavedRecipeUseCase {
    class GetAllSavedRecipes @Inject constructor(
        private val savedRepository: SavedRepository
    ) : SavedRecipeUseCase {
        operator fun invoke(): List<Int> = savedRepository.getAllSavedRecipes()
    }

    class SaveRecipe @Inject constructor(
        private val savedRepository: SavedRepository
    ) : SavedRecipeUseCase {
        operator fun invoke(id: Int) = savedRepository.saveRecipe(id)
    }

    class DeleteRecipe @Inject constructor(
        private val savedRepository: SavedRepository
    ) : SavedRecipeUseCase {
        operator fun invoke(id: Int) = savedRepository.deleteRecipe(id)
    }

    class IsSaved @Inject constructor(
        private val savedRepository: SavedRepository
    ) : SavedRecipeUseCase {
        operator fun invoke(id: Int): Boolean = savedRepository.isSaved(id)
    }
}