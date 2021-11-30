package com.example.el_menus_challenge.di.appmodules

import com.example.el_menus_challenge.util.AppSchedulerProvider
import com.example.el_menus_challenge.util.SchedulerProvider
import dagger.Module
import dagger.Provides


@Module
class RxModule {


    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }


}