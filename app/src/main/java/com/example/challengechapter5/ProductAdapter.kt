package com.example.challengechapter5

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter5.activity.DetailProductActivity
import com.example.challengechapter5.databinding.ItemProductBinding
import com.example.challengechapter5.model.ResponseDataProductItem

class ProductAdapter(private var listFilm : List<ResponseDataProductItem>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtJudulFilm.text = listFilm[position].name
        holder.binding.txtYearFilm.text = listFilm[position].price
        Glide.with(holder.itemView.context).load(listFilm[position].imageLink).into(holder.binding.ivItem)

        holder.binding.cardItem.setOnClickListener {
            val pinda = Intent(it.context, DetailProductActivity::class.java)
            pinda.putExtra("detail", listFilm[position])
            it.context.startActivity(pinda)
        }
    }

    override fun getItemCount(): Int = listFilm.size

}