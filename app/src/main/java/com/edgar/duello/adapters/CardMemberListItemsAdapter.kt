package com.edgar.duello.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edgar.duello.R
import com.edgar.duello.databinding.ItemCardSelectedMemberBinding
import com.edgar.duello.models.SelectedMembers

open class CardMemberListItemsAdapter(
    private val context: Context,
    private var list: ArrayList<SelectedMembers>,
    private val assignMembers: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_card_selected_member, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = ItemCardSelectedMemberBinding.bind(holder.itemView)
        val model = list[position]

        if (holder is MyViewHolder) {

            if (position == list.size - 1 && assignMembers) {
                binding.ivAddMember.visibility = View.VISIBLE
                binding.ivSelectedMemberImage.visibility = View.GONE
            } else {
                binding.ivAddMember.visibility = View.GONE
                binding.ivSelectedMemberImage.visibility = View.VISIBLE

                Glide
                    .with(context)
                    .load(model.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user_place_holder)
                    .into(binding.ivSelectedMemberImage)
            }

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick()
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
        fun onClick()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}