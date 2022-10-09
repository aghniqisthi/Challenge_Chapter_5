package com.example.challengechapter5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter5.databinding.ActivityProfileBinding
import com.example.challengechapter5.model.ViewModelUser
import java.util.*

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    lateinit var sharedpref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        val username = sharedpref.getString("username", "")
        val name = sharedpref.getString("name", "")
        val age = sharedpref.getInt("age", 0)
        val addr = sharedpref.getString("address", "")

        binding.editUsernameProf.setText(username)
        binding.editNamaProf.setText(name)
        binding.editAgeProf.setText(age.toString())
        binding.editAddressProf.setText(addr)

        binding.imageViewProf.setOnClickListener{

        }

        binding.btnUpdate.setOnClickListener {
            //ambil data
            val id = sharedpref.getString("id", "")
            val username = binding.editUsernameProf.text.toString()
            val password = sharedpref.getString("password", "")
            val name = binding.editNamaProf.text.toString()
            val age = binding.editAgeProf.text.toString().toInt()
            val addr = binding.editAddressProf.text.toString()

            //update data di api
            updateDataFilm(id!!.toInt(), name, username, password!!, age, addr)

            //update data di sharedpref
            var addUser = sharedpref.edit()
            addUser.putString("username", username)
            addUser.putString("name", name)
            addUser.putInt("age", age)
            addUser.putString("address", addr)
            addUser.apply()

            val pinda = Intent(this, HomeActivity::class.java)
            startActivity(pinda)
        }

        binding.btnLogout.setOnClickListener {
            var addUser = sharedpref.edit()
            addUser.putString("username", "")
            addUser.putString("password", "")
            addUser.apply()

            val pindah = Intent(this, LoginActivity::class.java)
            startActivity(pindah)
        }
    }

    fun updateDataFilm(id : Int, name : String, username : String, password:String, age : Int, addr : String) {
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.callEditUser(id, name, username, password, age, addr)
        viewModel.editLiveDataUser().observe(this, {
            if (it != null) {
                Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show()
            }
        })
    }



    fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }
}