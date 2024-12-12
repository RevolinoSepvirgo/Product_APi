package com.revolino.produk_api

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.revolino.produk_api.adapter.ProdukAdapter
import com.revolino.produk_api.model.ResponseProduk
import com.revolino.produk_api.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ResponseProduk>
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.rv_products)


        produkAdapter = ProdukAdapter { modelProduk: ModelProduk -> prodcutOnclik(modelProduk) }
        recyclerView.adapter = produkAdapter

        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext, LinearLayoutManager.VERTICAL, false
        )

        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()
    }

    private fun getData() {
        swipeRefreshLayout.isRefreshing=true
        call = ApiClient.produkService.getProduk()
        call.enqueue(object :Callback<ResponseProduk> {

            override fun onResponse(
                call: Call<ResponseProduk>,
                response: Response<ResponseProduk>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    produkAdapter.submitList(response.body()?.products)
                    produkAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseProduk>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun prodcutOnclik(modelProduk: ModelProduk) {
        Toast.makeText(applicationContext,modelProduk.description,
            Toast.LENGTH_SHORT)
    }


}




