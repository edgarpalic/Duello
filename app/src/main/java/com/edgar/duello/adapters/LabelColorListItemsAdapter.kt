package com.edgar.duello.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edgar.duello.R
import com.edgar.duello.databinding.ItemLabelColorBinding
import java.util.ArrayList

class LabelColorListItemsAdapter(
    private val context: Context,
    private var list: ArrayList<String>,
    private val mSelectedColor: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_label_color,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = ItemLabelColorBinding.bind(holder.itemView)
        val item = list[position]

        if (holder is MyViewHolder) {
            binding.viewMain.setBackgroundColor(Color.parseColor(item))

            if (item == mSelectedColor) {
                binding.ivSelectedColor.visibility = View.VISIBLE
            } else {
                binding.ivSelectedColor.visibility = View.GONE
            }
            holder.itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener!!.onClick(position, item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener {

        fun onClick(position: Int, color: String)
    }
}