package org.positive.daymotion.presentation.category.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.positive.daymotion.databinding.ItemMissionHistoryBinding
import org.positive.daymotion.extension.layoutInflater
import org.positive.daymotion.presentation.category.adapter.holder.MissionHistoryViewHolder
import org.positive.daymotion.presentation.category.model.MissionHistoryItem

class MissionHistoryAdapter : RecyclerView.Adapter<MissionHistoryViewHolder>() {

    private val items = mutableListOf<MissionHistoryItem>()

    fun replaceAll(items: List<MissionHistoryItem>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MissionHistoryViewHolder {
        val layoutInflater = parent.context.layoutInflater
        val binding = ItemMissionHistoryBinding.inflate(layoutInflater, parent, false)
        return MissionHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MissionHistoryViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun onViewRecycled(holder: MissionHistoryViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val current = items[position]
            items[position] = current.copy(
                savedPosition = holder.innerScrollPosition,
                savedPositionOffset = holder.innerScrollPositionOffset
            )
        }
    }
}