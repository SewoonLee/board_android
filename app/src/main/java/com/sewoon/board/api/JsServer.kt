package com.sewoon.board.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsServer {
    companion object{
//        var gsonBuilder = GsonBuilder()
        private const val url = "http://10.0.2.2:8080/api/"
        private var server:Retrofit=Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var boardApi:BoardApi = server.create(BoardApi::class.java)
    }
}