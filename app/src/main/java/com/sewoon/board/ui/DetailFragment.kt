package com.sewoon.board.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sewoon.board.BoardAdapter
import com.sewoon.board.MainViewModel
import com.sewoon.board.R
import com.sewoon.board.databinding.DetailFragmentBinding
import com.sewoon.board.model.Board

class DetailFragment : Fragment() {
    private var _binding:DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = BoardAdapter()
    private val viewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentBoard.observe(viewLifecycleOwner) {
            Log.d("current_Board", it.toString())
            binding.titleTv.text = it.title
            binding.contentTv.text = it.content
            binding.dateTv.text = it.created_date.toString()
            viewModel.getBoard()
        }

        binding.rewriteButton.setOnClickListener {
            viewModel.setEditBoard(viewModel.currentBoard.value!!)
            findNavController().navigate(R.id.action_detailFragment_to_editFragment)
        }

        binding.returnListButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}