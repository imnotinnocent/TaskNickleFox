package com.example.myapplication.API

import com.example.myapplication.DATA.Item
import com.example.myapplication.DATA.ResponseModel
import com.example.myapplication.DATA.Snippet
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("search")
    suspend fun getAllResponse(
        @Query("part") part:String,
        @Query("q") q:String,
        @Query("key") key:String
    ) : ResponseModel
}