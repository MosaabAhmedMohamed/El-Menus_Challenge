package com.example.el_menus_challenge.di

import android.app.Application
import com.example.el_menus_challenge.app.ElmenusChallengeApp
import com.example.el_menus_challenge.di.appmodules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class]
)
interface AppComponent {
    fun inject(app: ElmenusChallengeApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}