//package com.example.myapplication.PAGINATION
//
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.liveData
//
//class MoviePagingRepo {
//    fun getPagesList() = Pager(
//        config = PagingConfig(
//            pageSize = 0
//        ),
//        pagingSourceFactory = { MoviePagingSource() }
//    ).liveData
//}