package com.arttseng.cathaybk.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arttseng.cathaybk.tools.RetrofitFactory
import com.arttseng.cathaybk.tools.UserDetail
import com.arttseng.cathaybk.tools.UserInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailViewModel() : ViewModel() {

    private var disContainer : CompositeDisposable = CompositeDisposable()
    private var userDetail : MutableLiveData<UserDetail> = MutableLiveData()
    private var error : MutableLiveData<String> = MutableLiveData()
    private var msg : MutableLiveData<Int> = MutableLiveData()

    val testUserDetail = """
        [{
    "login": "defunkt",
  "id": 2,
  "node_id": "MDQ6VXNlcjI=",
  "avatar_url": "https://avatars.githubusercontent.com/u/2?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/defunkt",
  "html_url": "https://github.com/defunkt",
  "followers_url": "https://api.github.com/users/defunkt/followers",
  "following_url": "https://api.github.com/users/defunkt/following{/other_user}",
  "gists_url": "https://api.github.com/users/defunkt/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/defunkt/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/defunkt/subscriptions",
  "organizations_url": "https://api.github.com/users/defunkt/orgs",
  "repos_url": "https://api.github.com/users/defunkt/repos",
  "events_url": "https://api.github.com/users/defunkt/events{/privacy}",
  "received_events_url": "https://api.github.com/users/defunkt/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Chris Wanstrath",
  "company": null,
  "blog": "http://chriswanstrath.com/",
  "location": null,
  "email": null,
  "hireable": null,
  "bio": "🍔",
  "twitter_username": null,
  "public_repos": 107,
  "public_gists": 273,
  "followers": 21159,
  "following": 210,
  "created_at": "2007-10-20T05:24:19Z",
  "updated_at": "2019-11-01T21:56:00Z"
        }]
    """.trimIndent()

    private fun genUserDetail():UserDetail? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, UserDetail::class.java)
        val adapter = moshi.adapter<List<UserDetail>>(type)
        return adapter.fromJson(testUserDetail)?.get(0)
    }


    init {
        //getAllUser()
    }

    //Api
    fun getUserDetailAPI(loginname: String){
        MainScope().launch(Dispatchers.IO) {
//            val webResponse = RetrofitFactory.WebAccess.API.getUserDetail(loginname).await()
//            var data : UserDetail? = if (webResponse.isSuccessful) {
//                webResponse.body()
//                //Log.d("TEST", data?.toString())
//            } else {
//                Log.d("TEST", "Error ${webResponse.code()}:${webResponse.message()}")
//                //Log.d("TEST", "Error ${webResponse.message()}")
//                genUserDetail()
//            }

            var data : UserDetail? = genUserDetail()
            MainScope().launch(Dispatchers.Main) {
                userDetail.value = data
            }

        }
    }



    //Getter
    fun getUserDetail() : MutableLiveData<UserDetail> {
        return userDetail
    }

    fun getError() : MutableLiveData<String>{
        return error
    }

    fun getMsg() : MutableLiveData<Int>{
        return msg
    }

    fun clearCancel(){
        disContainer.clear()
    }

}