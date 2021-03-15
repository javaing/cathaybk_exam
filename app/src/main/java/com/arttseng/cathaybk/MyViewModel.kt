package com.arttseng.cathaybk


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arttseng.cathaybk.tools.RetrofitFactory
import com.arttseng.cathaybk.tools.UserInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MyViewModel() : ViewModel() {

    private var disContainer : CompositeDisposable = CompositeDisposable()
    private var allHost : MutableLiveData<ArrayList<UserInfo>> = MutableLiveData()
    private var error : MutableLiveData<String> = MutableLiveData()
    private var msg : MutableLiveData<Int> = MutableLiveData()

    init {
        getAllHostApi()
    }

    //Api
    fun getAllHostApi(){
//        val observer = object : ObserverOnNextListener<UserInfo>{
//            override fun onSubscribe(d: Disposable) {
//                disContainer.add(d)
//            }
//
//            override fun onNext(data: UserInfo) {
//                val result : ArrayList<UserInfo>? = data.result
//                if(result != null && result.isNotEmpty()){
//                    allHost.value = result
//                    setLiveHost(result)
//                } else {
//                    allHost.value = ArrayList()
//                }
//            }
//
//            override fun onComplete() {
//
//            }
//
//            override fun onError(e: Throwable) {
//            }
//        }
//        ApiMethods.getMoreHostApiNew(ProgressObserver<UserInfo>(null , observer, false))


        MainScope().launch(Dispatchers.IO) {
            val webResponse = RetrofitFactory.WebAccess.API.getUserList().await()
            var data : List<UserInfo>? = null
            if (webResponse.isSuccessful) {
                data = webResponse.body()
                //Log.d("TEST", data?.toString())
            } else {
                Log.d("TEST", "Error ${webResponse.code()}:${webResponse.message()}")
                //Log.d("TEST", "Error ${webResponse.message()}")
                data = genTestData()
            }

            MainScope().launch(Dispatchers.Main) {
                allHost.value = data as ArrayList<UserInfo>?
            }

        }


    }

    private fun genTestData():List<UserInfo>? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, UserInfo::class.java)
        val adapter = moshi.adapter<List<UserInfo>>(type)
        return adapter.fromJson(testStr)
    }

    val testStr = """
        [{
    "login": "mojombo",
    "id": 1,
    "node_id": "MDQ6VXNlcjE=",
    "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/mojombo",
    "html_url": "https://github.com/mojombo",
    "followers_url": "https://api.github.com/users/mojombo/followers",
    "following_url": "https://api.github.com/users/mojombo/following{/other_user}",
    "gists_url": "https://api.github.com/users/mojombo/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/mojombo/subscriptions",
    "organizations_url": "https://api.github.com/users/mojombo/orgs",
    "repos_url": "https://api.github.com/users/mojombo/repos",
    "events_url": "https://api.github.com/users/mojombo/events{/privacy}",
    "received_events_url": "https://api.github.com/users/mojombo/received_events",
    "type": "User",
    "site_admin": false
  },
  {
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
    "site_admin": false
  },
  {
    "login": "pjhyett",
    "id": 3,
    "node_id": "MDQ6VXNlcjM=",
    "avatar_url": "https://avatars.githubusercontent.com/u/3?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/pjhyett",
    "html_url": "https://github.com/pjhyett",
    "followers_url": "https://api.github.com/users/pjhyett/followers",
    "following_url": "https://api.github.com/users/pjhyett/following{/other_user}",
    "gists_url": "https://api.github.com/users/pjhyett/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/pjhyett/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/pjhyett/subscriptions",
    "organizations_url": "https://api.github.com/users/pjhyett/orgs",
    "repos_url": "https://api.github.com/users/pjhyett/repos",
    "events_url": "https://api.github.com/users/pjhyett/events{/privacy}",
    "received_events_url": "https://api.github.com/users/pjhyett/received_events",
    "type": "User",
    "site_admin": false
  }]
    """.trimIndent()


    //Getter
    fun getAllHost() : MutableLiveData<ArrayList<UserInfo>> {
        return allHost
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