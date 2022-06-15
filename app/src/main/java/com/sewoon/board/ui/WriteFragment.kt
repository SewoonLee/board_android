package com.sewoon.board.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sewoon.board.MainViewModel
import com.sewoon.board.R
import com.sewoon.board.databinding.WriteFragmentBinding
import com.sewoon.board.model.Board
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class WriteFragment : Fragment() {
    private var _binding:WriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.regButton.setOnClickListener{

            if(binding.titleEt.text.isEmpty()){
                Toast.makeText(this.context, "제목을 입력하세요.", Toast.LENGTH_SHORT).show()
                binding.titleEt.requestFocus()
            } else if(binding.contentEt.text.isEmpty()){
                Toast.makeText(this.context, "내용을 입력하세요.", Toast.LENGTH_SHORT).show()
                binding.contentEt.requestFocus()
            } else if(binding.pwEt.text.isEmpty()){
                Toast.makeText(this.context, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                binding.pwEt.requestFocus()
            } else {
                Toast.makeText(this.context, "${binding.titleEt.text}", Toast.LENGTH_SHORT).show()

                val createAt: Date = Date()
                val board:Board = Board(null, binding.titleEt.text.toString(), binding.contentEt.text.toString(), null, null, null, binding.pwEt.text.toString())
                val currentBoard:Board = Board(null, binding.titleEt.text.toString(), binding.contentEt.text.toString(), null, createAt, null, binding.pwEt.text.toString())
                viewModel.postBoard(board)
                viewModel.getBoard()
                viewModel.setCurrentBoard(currentBoard)

                findNavController().navigate(R.id.action_writeFragment_to_detailFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}