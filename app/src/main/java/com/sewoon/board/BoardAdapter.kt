package com.sewoon.board

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sewoon.board.model.Board

class BoardAdapter:RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {


    private var data:List<Board> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data:List<Board>) {
        this.data = data
        Log.d("setData", data.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_board_list, parent, false)

        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = data[position]
        holder.textTitle.text = item.title
        holder.textTime.text = item.created_date.toString()
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    class BoardViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val textTitle:TextView = view.findViewById(R.id.textTitle)
        val textTime:TextView = view.findViewById(R.id.textTime)
        init {
            view.setOnClickListener{
                Log.d("Click_Click_Click_Click_Click", "${this.layoutPosition}번째 클릭됨.");
            }
        }

    }

}