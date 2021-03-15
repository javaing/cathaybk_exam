package com.arttseng.cathaybk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arttseng.cathaybk.tools.UserInfo
import com.arttseng.cathaybk.databinding.ItemUserinfoBinding
import com.arttseng.cathaybk.tools.roundImage

class MyAdapter: RecyclerView.Adapter<MyAdapter.mViewHolder>() {

    var unAssignList = listOf<UserInfo>()

    inner class mViewHolder : RecyclerView.ViewHolder {

        constructor(dealBind : ItemUserinfoBinding) : super(dealBind.root){
            initView(dealBind)
        }


        lateinit var  img_avatar : ImageView
        lateinit var  name : TextView

        lateinit var  parent : View

        private fun initView(dealBind : ItemUserinfoBinding){
            img_avatar = dealBind.imgAvatar
            name = dealBind.tvName

            parent = dealBind.root
        }

        fun bind(data: UserInfo) {
            name.text = data.login
            img_avatar.roundImage(data.avatar_url)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):mViewHolder {

        //載入項目模板
        val inflater = LayoutInflater.from(parent.context)

        val dealBind : ItemUserinfoBinding = ItemUserinfoBinding.bind(inflater.inflate(R.layout.item_userinfo, parent, false))
        return mViewHolder(dealBind)

    }

    override fun getItemCount() = unAssignList.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

        //呼叫上面的bind方法來綁定資料
        holder.bind(unAssignList[position])

    }

    //更新資料用
    fun updateList(list:List<UserInfo>){
        unAssignList = list
    }
}