package com.example.myapplication.API

import com.example.myapplication.DATA.Item

class ResponseRepository {
    private fun getService(): Service = Network.getRetrofit().create(Service::class.java)
    suspend fun getDataFromService(): List<Item> {
        return getService().getAllResponse("snippet","everything",50,"AIzaSyBydQ2cs7uwj1FcTxRuvFFZ7GPO3QcK2tM").items
    }
}