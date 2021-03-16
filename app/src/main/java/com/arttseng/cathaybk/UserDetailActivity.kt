package com.arttseng.cathaybk

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arttseng.cathaybk.databinding.ActivityUserdetailBinding
import com.arttseng.cathaybk.tools.UserDetail
import com.arttseng.cathaybk.tools.roundImage
import com.arttseng.cathaybk.viewmodel.DetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var dealVM : DetailViewModel
    private lateinit var binding: ActivityUserdetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserdetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dealVM = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        initObserve()

        val loginname:String = intent.getStringExtra("loginname").toString()
        dealVM.getUserDetailAPI(loginname)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initObserve() {
        dealVM.getUserDetail().observe(this, Observer {
            updateUI(it)
        })
    }


    private fun updateUI(data:UserDetail) {
        Log.d("TEST", "do ui")
        binding.tvName.text = data.login
        binding.imgAvatar.roundImage( data.avatar_url)
    }
}