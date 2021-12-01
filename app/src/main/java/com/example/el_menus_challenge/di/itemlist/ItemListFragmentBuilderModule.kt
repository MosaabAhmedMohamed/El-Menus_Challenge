package com.example.el_menus_challenge.di.itemlist

import com.example.presentation.itemlist.ui.fragment.ItemListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ItemListModule::class])
abstract class ItemListFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideItemListFragment(): ItemListFragment

}