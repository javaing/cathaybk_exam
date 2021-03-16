package com.arttseng.cathaybk.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arttseng.cathaybk.tools.RetrofitFactory
import com.arttseng.cathaybk.tools.UserDetail
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailViewModel() : ViewModel() {

    private var disContainer : CompositeDisposable = CompositeDisposable()
    //private var allHost : MutableLiveData<ArrayList<UserInfo>> = MutableLiveData()
    private var userDetail : MutableLiveData<UserDetail> = MutableLiveData()
    private var error : MutableLiveData<String> = MutableLiveData()
    private var msg : MutableLiveData<Int> = MutableLiveData()

    val testUserDetail = """
        {
          "login": "bmizerany",
          "id": 46,
          "node_id": "MDQ6VXNlcjQ2",
          "avatar_url": "https://avatars.githubusercontent.com/u/46?v=4",
          "gravatar_id": "",
          "url": "https://api.github.com/users/bmizerany",
          "html_url": "https://github.com/bmizerany",
          "followers_url": "https://api.github.com/users/bmizerany/followers",
          "following_url": "https://api.github.com/users/bmizerany/following{/other_user}",
          "gists_url": "https://api.github.com/users/bmizerany/gists{/gist_id}",
          "starred_url": "https://api.github.com/users/bmizerany/starred{/owner}{/repo}",
          "subscriptions_url": "https://api.github.com/users/bmizerany/subscriptions",
          "organizations_url": "https://api.github.com/users/bmizerany/orgs",
          "repos_url": "https://api.github.com/users/bmizerany/repos",
          "events_url": "https://api.github.com/users/bmizerany/events{/privacy}",
          "received_events_url": "https://api.github.com/users/bmizerany/received_events",
          "type": "User",
          "site_admin": false,
          "name": "Blake Mizerany",
          "company": null,
          "blog": "",
          "location": null,
          "email": null,
          "hireable": null,
          "bio": null,
          "twitter_username": null,
          "public_repos": 157,
          "public_gists": 166,
          "followers": 1289,
          "following": 19,
          "created_at": "2008-01-24T04:44:30Z",
          "updated_at": "2021-03-13T19:11:34Z"
        }
    """.trimIndent()

    private fun genUserDetail():UserDetail? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(UserDetail::class.java, UserDetail::class.java)
        val adapter = moshi.adapter<UserDetail>(type)
        return adapter.fromJson(testUserDetail)
    }


    init {
        //getAllUser()
    }

    //Api
    fun getUserDetailAPI(loginname: String){
        MainScope().launch(Dispatchers.IO) {
            val webResponse = RetrofitFactory.WebAccess.API.getUserDetail(loginname).await()
            var data : UserDetail? = if (webResponse.isSuccessful) {
                webResponse.body()
                //Log.d("TEST", data?.toString())
            } else {
                Log.d("TEST", "Error ${webResponse.code()}:${webResponse.message()}")
                //Log.d("TEST", "Error ${webResponse.message()}")
                genUserDetail()
            }

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