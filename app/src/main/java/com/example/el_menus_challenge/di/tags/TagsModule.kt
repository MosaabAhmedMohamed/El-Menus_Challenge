package com.example.el_menus_challenge.di.tags

import androidx.paging.ExperimentalPagingApi
import com.example.data.core.db.ElmenusChallengeDatabase
import com.example.data.tags.repository.TagsRepositoryImpl
import com.example.data.tags.source.TagsDataSource
import com.example.data.tags.source.remote.client.TagsApi
import com.example.domain.tags.repository.TagsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class TagsModule {


    @Provides
    fun provideTagsApi(retrofit: Retrofit.Builder): TagsApi {
        return retrofit
            .build()
            .create(TagsApi::class.java)
    }


    @ExperimentalPagingApi
    @Provides
    fun provideTagsRepository(
        tagsDataSource: TagsDataSource,
        db: ElmenusChallengeDatabase
    ): TagsRepository =
        TagsRepositoryImpl(tagsDataSource,db)


}