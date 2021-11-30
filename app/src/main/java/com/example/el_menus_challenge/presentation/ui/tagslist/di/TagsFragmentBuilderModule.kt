package com.example.el_menus_challenge.presentation.ui.tagslist.di

import com.example.el_menus_challenge.presentation.ui.tagslist.ui.fragment.TagsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [TagsModule::class])
abstract class TagsFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideTagsFragment(): TagsFragment

}