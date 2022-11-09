package com.example.challengechapter5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challengechapter5.databinding.ActivityLoginBinding
import com.example.challengechapter5.model.ResponseDataUserItem
import com.example.challengechapter5.network.RetrofitClientUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    lateinit var sharedpref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            //data inputan user
            val inputUsername = binding.editUsernameLog.text.toString()
            val inputPass = binding.editPasswordLog.text.toString()

            if(inputUsername != null && inputPass !=null) requestLogin(inputUsername, inputPass)
            else if(inputUsername == null && inputPass == null) toast("Empty Username or Password!")
        }

        binding.txtRegister.setOnClickListener{
            val pindah = Intent(this, RegisterActivity::class.java)
            startActivity(pindah)
        }

        binding.txtLangEn.setOnClickListener{
            setLocale("en")
        }
        binding.txtLangIdn.setOnClickListener{
            setLocale("id")
        }
        binding.txtLangKor.setOnClickListener{
            setLocale("ko")
        }
    }

    fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }

    private fun requestLogin(username:String, password:String){
        RetrofitClientUser.instance.getAllUser().enqueue(object : Callback<List<ResponseDataUserItem>> {
            override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                var data = false
                if(response.isSuccessful){
                    if(response.body() != null){
                        val respon = response.body()
                        for (i in 0 until respon!!.size){
                            if(respon[i].username == username && respon[i].password == password){
                                data = true

                                //add ke sharedpref
                                val addUser = sharedpref.edit()
                                addUser.putString("id", respon[i].id)
                                addUser.putString("username", username)
                                addUser.putString("password", password)
                                addUser.putString("name", respon[i].name)
                                addUser.putInt("age", respon[i].age)
                                addUser.putString("address", respon[i].address)
                                addUser.apply()

                                toast("Login Success!")
                                val pinda = Intent(this@LoginActivity, HomeActivity::class.java)
                                startActivity(pinda)
                            }
                        }
                        if(!data) toast("Wrong Username or Password!")
                    }
                    else toast("Empty Response!")
                }
                else toast("Failed Load Data!")
            }

            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setLocale(lang:String){
        val lokal = Locale(lang)
        val conf = resources.configuration
        conf.locale = lokal
        resources.updateConfiguration(conf, resources.displayMetrics)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}