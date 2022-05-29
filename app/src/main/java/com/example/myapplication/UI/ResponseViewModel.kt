package com.example.myapplication.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.API.NetworkHelperClass
import com.example.myapplication.API.ResponseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResponseViewModel : ViewModel() {
    private val responseRepository = ResponseRepository()
    private val mutableLiveData = MutableLiveData<NetworkHelperClass>()
    val liveData: LiveData<NetworkHelperClass> = mutableLiveData
    fun callAPi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = responseRepository.getDataFromService()
            mutableLiveData.postValue(NetworkHelperClass.OnSuccess(response))
        }
    }
}