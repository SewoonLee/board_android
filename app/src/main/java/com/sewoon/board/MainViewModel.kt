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
    val currentBoard = MutableLiveData<Board>()

    val all_board = MutableLiveData<List<Board>>()
    lateinit var request_board: Call<Board>
    lateinit var request_all_board: Call<List<Board>>
    lateinit var request_post_board: Call<String>

    fun setCurrentBoard(board:Board) = viewModelScope.launch {
        currentBoard.value = board
    }

    fun getCurrentBoard(board:Board):Board? {
        return currentBoard.value
    }


    fun postBoard(board: Board) = viewModelScope.launch {
        request_post_board = JsServer.boardApi.postBoard(board)
        request_post_board.enqueue(object:Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("POST", "POST result: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("POST", "ERROR ${error.value.toString()}")
            }
        })
    }

    fun getBoard(id: Int) = viewModelScope.launch {
        request_board = JsServer.boardApi.getBoard(id)
        request_board.enqueue(object:Callback<Board>{
            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                currentBoard.value = response.body()
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