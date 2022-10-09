package com.example.challengechapter5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.example.challengechapter5.R
import com.example.challengechapter5.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPref : SharedPreferences
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).asGif().load(R.drawable.gifsplash).into(binding.ivSplash)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var dbUser = sharedPref.getString("username", "")

        //splash : seleksi data di sharedpreferencenya ada atau ngga.
        if(dbUser == "") {
            var dbUsername = sharedPref.getString("username", "You!")
            var bundle = Bundle()
            bundle.putString("username", dbUsername)

            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 5000)
        }
        else {
            Handler().postDelayed({
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 5000)
        }
    }
}