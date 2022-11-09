@file:Suppress("SpellCheckingInspection")

package com.example.challengechapter5.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.challengechapter5.databinding.ActivityDetailProductBinding
import com.example.challengechapter5.model.ResponseDataProductItem

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailProductBinding
    private lateinit var dataFilm : ResponseDataProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dataFilm = intent.getSerializableExtra("detail") as ResponseDataProductItem

        if(dataFilm.productColors.isEmpty()) {
            binding.layoutDetailProduct.setBackgroundColor(Color.parseColor("#F8DBDE"))
        } else binding.layoutDetailProduct.setBackgroundColor(Color.parseColor(dataFilm.productColors[0].hexValue))

        binding.txtTitle.text = dataFilm.name
        binding.txtPrice.text = dataFilm.price
        binding.txtDesc.text = "${dataFilm.description}\n"
        Glide.with(this).load(dataFilm.imageLink).into(binding.imgProduct)

        binding.btnBackDet.setOnClickListener {
            val pindah = Intent(this, HomeActivity::class.java)
            startActivity(pindah)
        }
    }
}