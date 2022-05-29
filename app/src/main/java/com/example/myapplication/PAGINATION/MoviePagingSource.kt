//package com.example.myapplication.PAGINATION
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.myapplication.API.Network
//import com.example.myapplication.DATA.Item
//import com.example.myapplication.DATA.ResponseModel
//
//class MoviePagingSource: PagingSource<Int, Item>() {
//    private val apiClient = Network.getApiService()
//
//    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
//        val pageNumber = params.key ?: 1;
//
//        val movieResponse: ResponseModel = apiClient.getAllResponse("snippet","cricket",pageNumber,"AIzaSyBydQ2cs7uwj1FcTxRuvFFZ7GPO3QcK2tM")
//        val tvMovieResponseItem: ArrayList<Item> = movieResponse
//        return try {
//            LoadResult.Page(data = tvMovieResponseItem,prevKey = null,nextKey =
//            if (tvMovieResponseItem.isEmpty()) null else pageNumber + 1)
//        }
//        catch (e:Exception){
//            LoadResult.Error(e)
//        }
//    }
//}