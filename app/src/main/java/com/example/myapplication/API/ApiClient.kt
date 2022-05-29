package com.example.myapplication.API

import com.example.myapplication.DATA.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("search")
    suspend fun getAllResponse(
        @Query("part") part:String,
        @Query("q") q:String,
        @Query("maxResults") maxResults:Int,
        @Query("key") key:String
    ) : ResponseModel
}