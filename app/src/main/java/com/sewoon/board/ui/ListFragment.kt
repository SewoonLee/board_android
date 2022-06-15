package com.sewoon.board.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sewoon.board.BoardAdapter
import com.sewoon.board.MainViewModel
import com.sewoon.board.R
import com.sewoon.board.databinding.ListFragmentBinding
import com.sewoon.board.model.Board

class ListFragment : Fragment() {
    var data:List<Board> = listOf()

    private var _binding:ListFragmentBinding?=null
    private val binding get() = _binding!!
    private val adapter = BoardAdapter()
    private val viewModel:MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.regButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_writeFragment)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        viewModel.all_board.observe(viewLifecycleOwner){
            Log.d("RESPONSE", it.toString())
            val manager = LinearLayoutManager(activity)

            adapter.setData(it)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
            adapter.setListener{ v, position ->
                viewModel.currentBoard = MutableLiveData(adapter.getItem(position))
                findNavController().navigate(R.id.action_listFragment_to_detailFragment)
            }
        }

        binding.recentButton.setOnClickListener {
            adapter.sortByDate()
        }

        binding.mostViewButton.setOnClickListener {
            adapter.sortByView()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}