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
    fun interface OnItemClickListener{
        fun OnItemClick(v:View, position: Int)
    }
    private var listener:OnItemClickListener? = null



    private var data:List<Board> = listOf()

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private lateinit var current_board:Board

    fun sortByDate() {
        val comparator : Comparator<Board> = compareBy { it.created_date }
        val data = this.data.sortedWith(comparator).reversed()
        Log.d("sort_date", "$data")

        setData(data)
    }

    fun sortByView() {
        val comparator : Comparator<Board> = compareBy { it.hits }
        val data = this.data.sortedWith(comparator)
        Log.d("sort_view", "$data")

        setData(data)
    }

    fun setData(data:List<Board>) {
        this.data = data
        Log.d("setData", data.toString())
        notifyDataSetChanged()
    }

    fun getData(): List<Board> {
        return this.data
    }

    fun getItem(position: Int): Board{
        return this.data[position]
    }

    fun setCurrentBoard(board: Board) {
        this.current_board = board
        notifyDataSetChanged()
    }

    fun getCurrentBoard(): Board{
        return this.current_board
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_board_list, parent, false)

        return BoardViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = data[position]
        holder.textTitle.text = item.title
        holder.textTime.text = item.created_date.toString()

    }

    override fun getItemCount(): Int {

        Log.d("Data", "${this.data.size}개")
        return this.data.size
    }

    class BoardViewHolder(view: View, listener: OnItemClickListener?):RecyclerView.ViewHolder(view) {
        val textTitle:TextView = view.findViewById(R.id.textTitle)
        val textTime:TextView = view.findViewById(R.id.textTime)
        init {
            view.setOnClickListener{
                Log.d("Click_Click_Click_Click_Click", "${this.layoutPosition}번째 클릭됨. ${textTitle.text}, ${textTime.text}");
                listener?.OnItemClick(view, this.layoutPosition)
            }
        }


    }

}