package com.sewoon.board.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sewoon.board.BoardAdapter
import com.sewoon.board.MainViewModel
import com.sewoon.board.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private var _binding:ListFragmentBinding?=null
    private val binding get() = _binding!!
    private val adapter = BoardAdapter()
    private val viewModel:MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = LinearLayoutManager(activity)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = adapter
        }

        viewModel.all_board.observe(viewLifecycleOwner){
            Log.d("RESPONSE", it.toString())
            adapter.setData(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}