package com.revolino.produk_api.service



import com.revolino.produk_api.model.ResponseProduk
import retrofit2.Call
import retrofit2.http.GET

interface ProdukService {
    @GET("products")//end point
    fun getProduk(): Call<ResponseProduk>



}