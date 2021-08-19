package com.edgar.duello.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edgar.duello.R
import com.edgar.duello.databinding.ItemMemberBinding
import com.edgar.duello.models.User
import com.edgar.duello.utils.Constants

open class MemberListItemsAdapter(
    private val context: Context,
    private var list: ArrayList<User>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_member, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = ItemMemberBinding.bind(holder.itemView)
        val model = list[position]

        if (holder is MyViewHolder) {

            Glide
                .with(context)
                .load(model.image)
                .centerCrop()
                .placeholder(R.drawable.ic_user_place_holder)
                .into(binding.ivMemberImage)

            binding.tvMemberName.text = model.name
            binding.tvMemberEmail.text = model.email

            if (model.selected) {
                binding.ivSelectedMember.visibility = View.VISIBLE
            } else {
                binding.ivSelectedMember.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {

                if (onClickListener != null) {
                    if (model.selected) {
                        onClickListener!!.onClick(position, model, Constants.UN_SELECT)
                    } else {
                        onClickListener!!.onClick(position, model, Constants.SELECT)
                    }
                    // END
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
        fun onClick(position: Int, user: User, action: String)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}