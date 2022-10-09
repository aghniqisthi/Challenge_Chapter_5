package com.example.challengechapter5.model

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5.activity.HomeActivity
import com.example.challengechapter5.activity.LoginActivity
import com.example.challengechapter5.activity.ProfileActivity
import com.example.challengechapter5.network.RetrofitClientUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class ViewModelUser : ViewModel() {

    lateinit var liveDataUser : MutableLiveData<List<ResponseDataUserItem>>
    lateinit var postLDUser : MutableLiveData<ResponseDataUserItem>
    lateinit var putLDUser : MutableLiveData<List<ResponseDataUserItem>>

    init {
        liveDataUser = MutableLiveData()
        postLDUser = MutableLiveData()
        putLDUser = MutableLiveData()
        callAPIUser()
    }

    fun ambilLiveDataUser() : MutableLiveData<List<ResponseDataUserItem>> {
        return liveDataUser
    }

    fun addLiveDataUser() : MutableLiveData<ResponseDataUserItem> {
        return postLDUser
    }

    fun editLiveDataUser() : MutableLiveData<List<ResponseDataUserItem>>{
        return putLDUser
    }

    fun callEditUser(id:Int, name : String, username :String, password: String, age :Int, addr : String){
        RetrofitClientUser.instance.putUser(id, User(name, username, password, addr, age)).enqueue(object : Callback<List<ResponseDataUserItem>>{
            override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                if(response.isSuccessful){
                    putLDUser.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                HomeActivity()
            }
        })
    }

    fun callPostAPICar(name:String, username:String, password:String, address:String, age:Int){
        RetrofitClientUser.instance.addUser(User(name, username, password, address, age)).enqueue(object :
            Callback<ResponseDataUserItem> {
            override fun onResponse(call: Call<ResponseDataUserItem>, response: Response<ResponseDataUserItem>) {
                if(response.isSuccessful) postLDUser.postValue(response.body())
            }
            override fun onFailure(call: Call<ResponseDataUserItem>, t: Throwable) {
                LoginActivity()
            }

        })
    }

    fun callAPIUser(){
        GlobalScope.async {
            RetrofitClientUser.instance.getAllUser().enqueue(object : Callback<List<ResponseDataUserItem>>{
                override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                    if (response.isSuccessful){
                        liveDataUser.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {

                }
            })
        }
    }
}