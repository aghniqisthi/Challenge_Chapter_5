package com.example.challengechapter5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter5.ProductAdapter
import com.example.challengechapter5.databinding.ActivityHomeBinding
import com.example.challengechapter5.model.ViewModelProduct

class HomeActivity : AppCompatActivity() {

    lateinit var sharedpref : SharedPreferences
    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.txtWelcomeUser.setText(sharedpref.getString("username", ""))

        showData()

        binding.btnProfile.setOnClickListener {
            val pindah = Intent(this, ProfileActivity::class.java)
            startActivity(pindah)
        }
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    fun showData(){
        val viewModel = ViewModelProvider(this).get(ViewModelProduct::class.java)
        viewModel.getliveDataFilm().observe(this, Observer {
            if (it != null) {
                binding.rvFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvFilm.adapter = ProductAdapter(it)
            } else {
                Toast.makeText(this, "There is no data to show", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.callApiFilm()
    }

    fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }
}