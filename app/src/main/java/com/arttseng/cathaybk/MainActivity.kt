package com.arttseng.cathaybk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arttseng.cathaybk.databinding.ActivityMainBinding
import com.arttseng.cathaybk.tools.UserInfo

class MainActivity : AppCompatActivity() {

    private lateinit var dealVM : MyViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        dealVM = ViewModelProviders.of(this).get(MyViewModel::class.java)

        initObserve()
    }

    private fun initObserve() {
        dealVM.getAllHost().observe(this, Observer {
            updateUI(it)
        })
    }



    private fun updateUI(data:List<UserInfo>) {
        if(data.isNotEmpty()) {
            Log.d("TEST", "do ui")
            val mAdapter = MyAdapter()
            mAdapter.updateList(data)     //傳入資料
            binding.rvUser.layoutManager = LinearLayoutManager(this)
            binding.rvUser.adapter = mAdapter
        }
    }
}