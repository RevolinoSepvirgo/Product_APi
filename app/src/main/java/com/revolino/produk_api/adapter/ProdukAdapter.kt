package com.revolino.produk_api.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.revolino.produk_api.DetailProduk
import com.revolino.produk_api.ModelProduk
import com.revolino.produk_api.R

class ProdukAdapter(
    private val onClik: (ModelProduk) -> Unit

): ListAdapter<ModelProduk, ProdukAdapter.ProdukViewHolder>(ProductCallBack) {


    class ProdukViewHolder(ItemView: View, val onClik: (ModelProduk) -> Unit) :
        RecyclerView.ViewHolder(ItemView) {

        private val imgProduk: ImageView = itemView.findViewById(R.id.imgProduk)
        private val Title: TextView = itemView.findViewById(R.id.txtTitle)
        private val Brand: TextView = itemView.findViewById(R.id.txtBrand)
        private val Price: TextView = itemView.findViewById(R.id.txtPrice)
        val cardProduk: View = itemView.findViewById(R.id.cardProduk)

        private var currentProduk: ModelProduk? = null

        init {
            itemView.setOnClickListener() {
                currentProduk?.let {
                    onClik(it)

                }
            }

        }
        fun bind(produk: ModelProduk) {
            currentProduk = produk

            Title.text = produk.title
            Brand.text = produk.brand
            Price.text = produk.price.toString()

            Glide.with(itemView).load(produk.thumbnail).centerCrop()
                .into(imgProduk)
        }





    }


    object ProductCallBack : DiffUtil.ItemCallback<ModelProduk>() {
        override fun areItemsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
        R.layout.row_product_item, parent, false
        )
        return ProdukViewHolder(view, onClik)

    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = getItem(position)
        holder.bind(produk)
        holder.cardProduk.setOnClickListener (){
            val intent = Intent(holder.itemView.context, DetailProduk::class.java)
            intent.putExtra("title", produk.title)
            intent.putExtra("description", produk.description)
            intent.putExtra("thumbnail", produk.thumbnail)
            intent.putExtra("price", produk.price)
            intent.putExtra("stock", produk.stock)
            holder.itemView.context.startActivity(intent)
        }

    }
}
