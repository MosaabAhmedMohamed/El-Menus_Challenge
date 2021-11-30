package com.example.el_menus_challenge.di

import com.example.el_menus_challenge.presentation.ui.NavHostActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector
    abstract fun provideNavHostActivity():NavHostActivity
}