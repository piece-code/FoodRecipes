package uz.nargiz.foodrecipes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.nargiz.foodrecipes.presentation.screen.category.CategoryContract
import uz.nargiz.foodrecipes.presentation.screen.category.CategoryDirection
import uz.nargiz.foodrecipes.presentation.screen.detail.DetailContract
import uz.nargiz.foodrecipes.presentation.screen.detail.DetailDirection
import uz.nargiz.foodrecipes.presentation.screen.home.HomeContract
import uz.nargiz.foodrecipes.presentation.screen.home.HomeDirection
import uz.nargiz.foodrecipes.presentation.screen.search.SearchContract
import uz.nargiz.foodrecipes.presentation.screen.search.SearchDirection

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds]
    fun bindHome(impl: HomeDirection): HomeContract.Directions

    @[Binds]
    fun bindDetail(impl: DetailDirection): DetailContract.Directions

    @Binds
    fun bindSearch(impl: SearchDirection): SearchContract.Directions

    @Binds
    fun bindCategory(impl: CategoryDirection): CategoryContract.Directions
}