package com.example.myapplication.UI

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.API.NetworkHelperClass
import com.example.myapplication.DATA.Item
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var list = ArrayList<Item>()
    lateinit var responseAdapter : ResponseAdapter
    private lateinit var responseViewModel: ResponseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        responseViewModel = ViewModelProvider(this).get(ResponseViewModel::class.java)

        setAdapter()
        CoroutineScope(Dispatchers.IO).launch {
            responseViewModel.callAPi()
        }

        this.responseViewModel.liveData.observe(this) {
            it.let {
                when (it) {
                    is NetworkHelperClass.OnSuccess -> {
                        list = it.responseList as ArrayList<Item>
                        setAdapter()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun setAdapter() {
        responseAdapter = ResponseAdapter(this,list)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = responseAdapter
            layoutManager = linearLayoutManager
        }
    }
}