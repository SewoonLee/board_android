package com.sewoon.board.ui

import androidx.lifecycle.ViewModelProvider
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
import com.sewoon.board.databinding.EditFragmentBinding
import com.sewoon.board.model.Board

class EditFragment : Fragment() {

    private var _binding:EditFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.editBoard.observe(viewLifecycleOwner) {
            Log.d("edit_Board", it.toString())
            binding.titleEt.setText(it.title)
            binding.contentEt.setText(it.content)
        }

        binding.editButton.setOnClickListener {
            val originalBoard: Board = viewModel.getEditBoard()!!

            if(binding.pwEt.text.toString() == originalBoard.password) {
                val board: Board = Board(originalBoard.id, binding.titleEt.text.toString(), binding.contentEt.text.toString(), originalBoard.hits, null, null, binding.pwEt.text.toString())
                viewModel.updateBoard(board)

                board.created_date = originalBoard.created_date
                viewModel.setCurrentBoard(board)
                viewModel.getBoard()

                findNavController().navigate(R.id.action_editFragment_to_detailFragment)
            } else {
                Toast.makeText(this.context, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}