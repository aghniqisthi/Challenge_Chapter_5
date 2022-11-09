package com.example.challengechapter5.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter5.databinding.ActivityRegisterBinding
import com.example.challengechapter5.model.ViewModelUser

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //kalo imageview diklik, muncul permission buat ke camera foto trus muncul
        binding.imageView.setOnClickListener{

        }

        //seleksi pass = confpass
        binding.btnRegister.setOnClickListener {

            //ambil inputan data dari layout
            val editUsername = binding.editUsername.text.toString()
            val editName = binding.editNama.text.toString()
            val editPass = binding.editPassword.text.toString()
            val editConfPass = binding.editConfirmPassword.text.toString()
            val editAge = binding.editAge.text.toString()
            val editAddr = binding.editAddress.text.toString()

            //seleksi password
            if (editPass == editConfPass) {
                //masukin data ke API
                addUser(editName, editUsername, editPass, editAddr, editAge.toInt())
                //back to login
                val pindah = Intent(this, LoginActivity::class.java)
                startActivity(pindah)
            } else toast("Password Doesn't Match!")
        }
    }

    private fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    private fun addUser(name:String, username:String, password:String, address:String, age:Int){
        val viewModel = ViewModelProvider(this)[ViewModelUser::class.java]
        viewModel.callPostAPIUser(name, username, password, address, age)
        viewModel.addLiveDataUser().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Registration Success!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}