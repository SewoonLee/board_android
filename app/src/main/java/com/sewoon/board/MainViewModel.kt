package com.sewoon.board


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sewoon.board.api.JsServer
import com.sewoon.board.model.Board
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val error = MutableLiveData<String>()
    val board = MutableLiveData<Board>()
    val all_board = MutableLiveData<List<Board>>()
    lateinit var request_board: Call<Board>
    lateinit var request_all_board: Call<List<Board>>


    fun getBoard(id: Int) = viewModelScope.launch {
        request_board = JsServer.boardApi.getBoard(id)
        request_board.enqueue(object:Callback<Board>{
            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                board.value = response.body()
                Log.d("RESPONSE", "Response: ${response.code()}")
            }

            override fun onFailure(call: Call<Board>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("ERROR", "Error: ${error.value.toString()}")
            }
        })
    }

    fun getBoard() = viewModelScope.launch {
        request_all_board = JsServer.boardApi.getAllBoard()
        request_all_board.enqueue(object:Callback<List<Board>>{
            override fun onResponse(call: Call<List<Board>>, response: Response<List<Board>>) {
                all_board.value = response.body()
                Log.d("RESPONSE", "Response: ${response.code()}")
            }

            override fun onFailure(call: Call<List<Board>>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("ERROR", "Error: ${error.value.toString()}")
            }
        })

    }

    override fun onCleared() {
        super.onCleared()
        if(::request_board.isInitialized) request_board.cancel()
    }
}