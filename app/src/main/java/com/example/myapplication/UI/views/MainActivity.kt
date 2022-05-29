package com.example.myapplication.UI.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.API.NetworkHelperClass
import com.example.myapplication.DATA.Item
import com.example.myapplication.R
import com.example.myapplication.UI.ResponseAdapter
import com.example.myapplication.UI.viewmodel.ResponseViewModel
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

        responseViewModel.liveData.observe(this) {
            it.let {
                when (it) {
                    is NetworkHelperClass.OnSuccess -> {
                        binding.shimmerFrameLayout.stopShimmer()
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.shimmerFrameLayout.visibility = View.GONE
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