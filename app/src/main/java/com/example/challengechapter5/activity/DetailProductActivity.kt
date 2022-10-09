package com.example.challengechapter5.activity

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.text.style.BackgroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.challengechapter5.databinding.ActivityDetailProductBinding
import com.example.challengechapter5.model.ResponseDataProductItem
import kotlinx.android.synthetic.main.activity_detail_product.*
import okhttp3.internal.toHexString


class DetailProductActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailProductBinding
    lateinit var dataFilm : ResponseDataProductItem

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