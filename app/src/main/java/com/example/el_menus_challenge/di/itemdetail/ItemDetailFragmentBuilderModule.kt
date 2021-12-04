package com.example.el_menus_challenge.di.itemdetail

import com.example.presentation.itemdetail.ui.fragment.ItemDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [ItemDetailModule::class])
abstract class ItemDetailFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideItemDetailFragment(): ItemDetailFragment
}