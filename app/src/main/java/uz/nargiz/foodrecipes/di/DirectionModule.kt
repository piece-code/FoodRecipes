package uz.nargiz.foodrecipes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.nargiz.foodrecipes.presentation.screen.detail.DetailContract
import uz.nargiz.foodrecipes.presentation.screen.detail.DetailDirection
import uz.nargiz.foodrecipes.presentation.screen.home.HomeContract
import uz.nargiz.foodrecipes.presentation.screen.home.HomeDirection

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds]
    fun bindHome(impl: HomeDirection): HomeContract.Directions

    @[Binds]
    fun bindDetail(impl: DetailDirection): DetailContract.Directions
}