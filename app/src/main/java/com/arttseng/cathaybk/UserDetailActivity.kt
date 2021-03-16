package com.arttseng.cathaybk

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arttseng.cathaybk.databinding.ActivityUserdetailBinding
import com.arttseng.cathaybk.databinding.ActivityUserdetailRelativeBinding
import com.arttseng.cathaybk.tools.UserDetail
import com.arttseng.cathaybk.tools.roundImage
import com.arttseng.cathaybk.viewmodel.DetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var dealVM : DetailViewModel
    private lateinit var binding: ActivityUserdetailRelativeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserdetailRelativeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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
        binding.imgAvatar.roundImage( data.avatar_url)
        binding.tvName.text = data.name
        binding.tvLogin.text = data.login
        binding.tvStaff.visibility = if(data.site_admin) View.VISIBLE else View.INVISIBLE
        binding.tvBio.text = data.bio
        binding.tvBlog.text = data.blog

        binding.tvBlog.autoLinkMask = Linkify.WEB_URLS;
        binding.tvBlog.movementMethod = LinkMovementMethod.getInstance();
    }
}