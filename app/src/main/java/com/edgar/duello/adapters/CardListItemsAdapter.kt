package com.edgar.duello.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edgar.duello.R
import com.edgar.duello.activities.TaskListActivity
import com.edgar.duello.databinding.ItemCardBinding
import com.edgar.duello.models.Card
import com.edgar.duello.models.SelectedMembers

open class CardListItemsAdapter(
    private val context: Context,
    private var list: ArrayList<Card>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = ItemCardBinding.bind(holder.itemView)
        val model = list[position]

        if (holder is MyViewHolder) {
            if (model.labelColor.isNotEmpty()) {
                binding.viewLabelColor.visibility = View.VISIBLE
                binding.viewLabelColor.setBackgroundColor(Color.parseColor(model.labelColor))
            } else {
                binding.viewLabelColor.visibility = View.GONE
            }

            binding.tvCardName.text = model.name

            if ((context as TaskListActivity).mAssignedMembersDetailList.size > 0) {
                val selectedMembersList: ArrayList<SelectedMembers> = ArrayList()

                for (i in context.mAssignedMembersDetailList.indices) {
                    for (j in model.assignedTo) {
                        if (context.mAssignedMembersDetailList[i].id == j) {
                            val selectedMember = SelectedMembers(
                                context.mAssignedMembersDetailList[i].id,
                                context.mAssignedMembersDetailList[i].image
                            )
                            selectedMembersList.add(selectedMember)
                        }
                    }
                }

                if (selectedMembersList.size > 0) {
                    binding.rvCardSelectedMembersList.visibility = View.VISIBLE
                    binding.rvCardSelectedMembersList.layoutManager = GridLayoutManager(context, 4)
                    val adapter = CardMemberListItemsAdapter(context, selectedMembersList, false)
                    binding.rvCardSelectedMembersList.adapter = adapter

                    adapter.setOnClickListener(object : CardMemberListItemsAdapter.OnClickListener {
                        override fun onClick() {
                            if (onClickListener != null) {
                                onClickListener!!.onClick(position)
                            }
                        }
                    })

                } else {
                    binding.rvCardSelectedMembersList.visibility = View.GONE
                }
            }

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position)
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
        fun onClick(cardPosition: Int)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}