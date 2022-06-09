package com.sewoon.board.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class JsServer {

    companion object{
        private const val url = "http://10.0.2.2:8080/api/"
        private var server:Retrofit=Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var boardApi:BoardApi = server.create(BoardApi::class.java)
    }
}