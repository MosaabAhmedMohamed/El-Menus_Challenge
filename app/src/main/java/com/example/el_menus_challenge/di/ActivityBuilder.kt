package com.example.el_menus_challenge.di

import com.example.el_menus_challenge.presentation.ui.NavHostActivity
import com.example.el_menus_challenge.presentation.ui.tagslist.di.TagsFragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [TagsFragmentBuilderModule::class])
    abstract fun provideNavHostActivity():NavHostActivity
}