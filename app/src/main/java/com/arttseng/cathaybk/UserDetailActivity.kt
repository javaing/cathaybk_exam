package com.arttseng.cathaybk

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.arttseng.cathaybk.databinding.ActivityUserdetailBinding
import com.arttseng.cathaybk.tools.UserDetail
import com.arttseng.cathaybk.tools.roundImage
import com.arttseng.cathaybk.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

class UserDetailActivity : AppCompatActivity() {

    private lateinit var dealVM : DetailViewModel
    private lateinit var binding: ActivityUserdetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserdetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val loginname:String = intent.getStringExtra("loginname").toString()
        dealVM = ViewModelProviders.of(this, MyViewModelFactory(loginname)).get(DetailViewModel::class.java)

        lifecycleScope.launch {
            val data = dealVM.loadUserDetail()
            updateUI(data)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateUI(data:UserDetail) {
        binding.imgAvatar.roundImage( data.avatar_url)
        binding.tvName.text = data.name
        binding.tvLogin.text = data.login
        binding.tvStaff.visibility = if(data.site_admin) View.VISIBLE else View.INVISIBLE
        binding.tvBio.text = data.bio
        binding.tvLocation.text = data.location
        binding.tvBlog.text = data.blog

        binding.tvBlog.autoLinkMask = Linkify.WEB_URLS;
        binding.tvBlog.movementMethod = LinkMovementMethod.getInstance();
    }
}

class MyViewModelFactory(val loginName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(String::class.java)
            .newInstance(loginName)
}