package com.example.data.tags.source.remote.client

import com.example.data.tags.source.remote.model.Tags
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TagsApi {

    @GET("/tags/{PAGE}")
    fun getTags(@Path("PAGE") pageNumber: Int): Single<Tags>

}