package com.example.el_menus_challenge.di.tags

import com.example.presentation.tags.ui.fragment.TagsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [TagsModule::class])
abstract class TagsFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideTagsFragment(): TagsFragment

}