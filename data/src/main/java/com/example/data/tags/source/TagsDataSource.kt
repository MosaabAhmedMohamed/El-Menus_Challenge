package com.example.data.tags.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.example.data.core.db.ElmenusChallengeDatabase
import com.example.data.tags.source.local.model.TagLocalModel
import com.example.data.tags.source.local.model.TagRemoteKey
import com.example.data.tags.source.mapper.mapToLocalModels
import com.example.data.tags.source.remote.client.TagsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException
import javax.inject.Inject

@ExperimentalPagingApi
class TagsDataSource @Inject constructor(
    private val remote: TagsApi,
    private val local: ElmenusChallengeDatabase
) : RxRemoteMediator<Int, TagLocalModel>() {


    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, TagLocalModel>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map { handleLoadType(it, state) }
            .flatMap { page ->
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    remote.getTags(page)
                        .map { it.tags?.mapToLocalModels() }
                        .map { insertToDb(page, loadType, it) }
                        .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = false) }
                        .onErrorReturn { MediatorResult.Error(it) }
                }

            }
            .onErrorReturn { MediatorResult.Error(it) }
    }

    private fun handleLoadType(
        it: LoadType,
        state: PagingState<Int, TagLocalModel>
    ) = when (it) {
        LoadType.REFRESH -> {
             1
        }
        LoadType.PREPEND -> {
            val remoteKeys = getRemoteKeyForFirstItem(state)
                ?: throw InvalidObjectException("Result is empty")
            remoteKeys.prevKey ?: INVALID_PAGE
        }
        LoadType.APPEND -> {
            val remoteKeys = getRemoteKeyForLastItem(state)
                ?: throw InvalidObjectException("Result is empty")
            remoteKeys.nextKey ?: INVALID_PAGE
        }
    }

    private fun insertToDb(
        page: Int,
        loadType: LoadType,
        data: List<TagLocalModel>
    ): List<TagLocalModel> {
        clearOnRefresh(loadType)
        insertRemoteKeys(getKeys(page, data))
        insertTags(data)
        return data
    }

    private fun clearOnRefresh(loadType: LoadType) {
        if (loadType == LoadType.REFRESH) {
            local.tagsRemoteKeyDao().clearRemoteKeys().subscribe()
            local.tagsDao().deleteAllEntries().subscribe()
        }
    }

    private fun getKeys(
        page: Int,
        data: List<TagLocalModel>
    ): List<TagRemoteKey?> {
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        val keys = data.map {
            it.tagName?.let { it1 -> TagRemoteKey(id = it1, prevKey = prevKey, nextKey = nextKey) }
        }
        return keys
    }

    private fun insertTags(data: List<TagLocalModel>) {
        local.tagsDao().insertTags(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun insertRemoteKeys(keys: List<TagRemoteKey?>) {
        local.tagsRemoteKeyDao()
            .insertAll(keys)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, TagLocalModel>): TagRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { repo ->
            repo.tagName?.let { local.tagsRemoteKeyDao().remoteKeysByTagId(it) }
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, TagLocalModel>): TagRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { tagModel ->
                tagModel.tagName?.let { local.tagsRemoteKeyDao().remoteKeysByTagId(it) }
            }
    }

    companion object {
        const val INVALID_PAGE = -1
    }

}