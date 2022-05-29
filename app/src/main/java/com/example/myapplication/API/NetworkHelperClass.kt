package com.example.myapplication.API

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.myapplication.DATA.Item
import com.example.myapplication.DATA.Snippet

sealed class NetworkHelperClass {
    data class OnSuccess(val responseList: List<Item>) : NetworkHelperClass()
}
