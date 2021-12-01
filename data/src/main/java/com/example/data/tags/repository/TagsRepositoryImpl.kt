package com.example.data.tags.repository

import androidx.paging.*
import androidx.paging.rxjava2.flowable
import com.example.data.core.db.ElmenusChallengeDatabase
import com.example.data.tags.source.TagsDataSource
import com.example.data.tags.source.mapper.mapToDomainModel
import com.example.domain.tags.model.TagModel
import com.example.domain.tags.repository.TagsRepository
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalPagingApi
class TagsRepositoryImpl
@Inject constructor(
    private val tagsDataSource: TagsDataSource,
    private val local: ElmenusChallengeDatabase
) : TagsRepository {


    @ExperimentalCoroutinesApi
    override fun getTags(): Flowable<PagingData<TagModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 100,
                prefetchDistance = 2,
                initialLoadSize = 10
            ),
            remoteMediator = tagsDataSource,
            pagingSourceFactory = { local.tagsDao().getTags() }
        ).flowable.flatMap {
            Flowable.just(it.map { it.mapToDomainModel() })
        }
    }


}