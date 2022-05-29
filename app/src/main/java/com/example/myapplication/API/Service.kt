package com.example.myapplication.API

import com.example.myapplication.DATA.Item
import com.example.myapplication.DATA.ResponseModel
import com.example.myapplication.DATA.Snippet
import retrofit2.http.GET

interface Service {
    @GET("search?part=snippet&q=the weekend&key=AIzaSyBydQ2cs7uwj1FcTxRuvFFZ7GPO3QcK2tM")
    suspend fun getAllResponse(

    ) : ResponseModel
}