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
    lateinit var request: Call<Board>


    fun getBoard(id: Int) = viewModelScope.launch {
        request = JsServer.boardApi.getBoard(id)
        request.enqueue(object:Callback<Board>{
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

    override fun onCleared() {
        super.onCleared()
        if(::request.isInitialized) request.cancel()
    }
}