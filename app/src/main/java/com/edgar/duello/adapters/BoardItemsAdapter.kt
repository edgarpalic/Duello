package com.edgar.duello.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edgar.duello.R
import com.edgar.duello.activities.TaskListActivity
import com.edgar.duello.databinding.ItemBoardBinding
import com.edgar.duello.models.Board

open class BoardItemsAdapter(
    private val context: Context,
    private var list: ArrayList<Board>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_board, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = ItemBoardBinding.bind(holder.itemView)
        val model = list[position]

        if (holder is MyViewHolder) {
            Glide
                .with(context)
                .load(model.image)
                .centerCrop()
                .placeholder(R.drawable.ic_board_place_holder)
                .into(binding.ivBoardImage)

            binding.tvName.text = model.name
            binding.tvCreatedBy.text = "Created By : ${model.createdBy}"

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, model)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Board)
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}