package com.example.el_menus_challenge.di

import com.example.el_menus_challenge.NavHostActivity
import com.example.el_menus_challenge.di.itemlist.ItemListFragmentBuilderModule
import com.example.el_menus_challenge.di.tags.TagsFragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [TagsFragmentBuilderModule::class,ItemListFragmentBuilderModule::class])
    abstract fun provideNavHostActivity(): NavHostActivity
}