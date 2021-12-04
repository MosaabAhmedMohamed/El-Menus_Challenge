package com.example.el_menus_challenge.app

import android.app.Application
import com.example.el_menus_challenge.di.AppComponent
import com.example.el_menus_challenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ElmenusChallengeApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    private var appComponent: AppComponent? = null


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent?.inject(this)
    }
}