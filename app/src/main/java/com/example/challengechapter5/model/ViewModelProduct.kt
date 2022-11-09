@file:Suppress("ControlFlowWithEmptyBody")

package com.example.challengechapter5.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5.network.RetrofitClientProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProduct : ViewModel() {
    var liveDataFilm : MutableLiveData<List<ResponseDataProductItem>> = MutableLiveData()

    fun getliveDataFilm() : MutableLiveData<List<ResponseDataProductItem>> {
        return  liveDataFilm
    }

    fun callApiFilm(){
        RetrofitClientProduct.instance.getAllFilm().enqueue(object : Callback<List<ResponseDataProductItem>> {
            override fun onResponse(call: Call<List<ResponseDataProductItem>>, response: Response<List<ResponseDataProductItem>>) {
                if (response.isSuccessful){
                    liveDataFilm.postValue(response.body())
                }
                else{

                }
            }
            override fun onFailure(call: Call<List<ResponseDataProductItem>>, t: Throwable) {

            }
        })
    }
}