package com.revolino.produk_api

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailProduk : AppCompatActivity() {

    private lateinit var titleDetail: TextView
    private lateinit var imgDetail: ImageView
    private lateinit var descriptionDetail: TextView
    private lateinit var priceDetail: TextView
    private lateinit var stokDetail: TextView
    private lateinit var btnBack: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_produk)

        titleDetail = findViewById(R.id.titleDetail)
        imgDetail = findViewById(R.id.imgDetail)
        descriptionDetail = findViewById(R.id.descriptionDetail)
        priceDetail = findViewById(R.id.priceDetail)
        stokDetail = findViewById(R.id.stokDetail)
        btnBack = findViewById(R.id.btnBack)

        val judul= intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("description")
        val harga = intent.getDoubleExtra("price",0.0)
        val gambar = intent.getStringExtra("thumbnail")
        val stok = intent.getIntExtra("stock",0)

        titleDetail.text = judul
        Glide.with(this).load(gambar).centerCrop().into(imgDetail)
        descriptionDetail.text = deskripsi
        priceDetail.text = harga.toString()
        stokDetail.text = stok.toString()


        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}