package com.sewoon.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sewoon.board.databinding.ActivityMainBinding
import com.sewoon.board.model.Board
import com.sewoon.board.ui.ListFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewModel:MainViewModel by viewModels()

        viewModel.board.observe(this){
            Log.d("RESPONSE", it.toString())
        }

        viewModel.all_board.observe(this) {
            Log.d("get_board", it.toString())
        }

        viewModel.currentBoard.observe(this) {
            Log.d("current_board", it.toString())
        }


        viewModel.getBoard()

    }

}