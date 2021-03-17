package com.arttseng.cathaybk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arttseng.cathaybk.R
import com.arttseng.cathaybk.UserDetailActivity
import com.arttseng.cathaybk.databinding.ItemUserinfoBinding
import com.arttseng.cathaybk.tools.UserInfo
import com.arttseng.cathaybk.tools.roundImage

class MyAdapter: RecyclerView.Adapter<MyAdapter.mViewHolder>() {

    var dataList = listOf<UserInfo>()
    lateinit var ctx: Context

    inner class mViewHolder : RecyclerView.ViewHolder {

        constructor(dealBind : ItemUserinfoBinding) : super(dealBind.root){
            initView(dealBind)
            setItemClick()
        }

        private lateinit var  img_avatar : ImageView
        private lateinit var  name : TextView
        private lateinit var  tv_staff : TextView
        private lateinit var  parent : View

        private fun initView(dealBind : ItemUserinfoBinding){
            img_avatar = dealBind.imgAvatar
            name = dealBind.tvName
            tv_staff = dealBind.tvStaff

            parent = dealBind.root
        }

        private fun setItemClick(){
            parent.setOnClickListener {
                val intent = Intent(ctx, UserDetailActivity::class.java)
                intent.putExtra("loginname", (parent.tag as UserInfo).login)
                ctx.startActivity(intent)
            }
        }

        fun bind(data: UserInfo) {
            parent.tag = data
            name.text = data.login
            tv_staff.visibility = if(data.site_admin) View.VISIBLE else View.INVISIBLE
            img_avatar.roundImage(data.avatar_url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        ctx = parent.context
        //載入項目模板
        val inflater = LayoutInflater.from(parent.context)

        val dealBind : ItemUserinfoBinding = ItemUserinfoBinding.bind(inflater.inflate(R.layout.item_userinfo, parent, false))
        return mViewHolder(dealBind)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

        //呼叫上面的bind方法來綁定資料
        holder.bind(dataList[position])

    }

    //更新資料用
    fun updateList(list:List<UserInfo>){
        dataList = list
    }
}