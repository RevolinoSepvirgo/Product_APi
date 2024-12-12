package com.revolino.produk_api

data class ModelProduk(
    val id : Int,
    val title : String,
    val description : String,
    val category : String,
    val price : Double,
    val brand : String,
    val stock : Int,
    val thumbnail : String,

)
