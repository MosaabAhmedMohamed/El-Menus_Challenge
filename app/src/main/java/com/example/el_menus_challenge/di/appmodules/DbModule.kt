package com.example.el_menus_challenge.di.appmodules

import android.content.Context
import androidx.room.Room
import com.example.data.core.db.ElmenusChallengeDatabase
import com.example.el_menus_challenge.util.data.DbConst
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideElmenusChallengeDatabase( context: Context): ElmenusChallengeDatabase {
        return Room.databaseBuilder(context, ElmenusChallengeDatabase::class.java, DbConst.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}