package com.sewoon.board.api

import com.sewoon.board.model.Board
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardApi {
    @GET("board/all")
    fun getAllBoard(): Call<List<Board>>

    @GET("board/{id}")
    fun getBoard(@Path("id") id:Int):Call<Board>
}