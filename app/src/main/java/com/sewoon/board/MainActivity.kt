package com.sewoon.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.sewoon.board.databinding.ActivityMainBinding
import com.sewoon.board.volley.SingletonQueue

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel.board.observe(this){
            Log.d("RESPONSE", it.content)
        }

        viewModel.getBoard(1)

    }
}