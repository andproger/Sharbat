package com.example.sharbat.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sharbat.R
import com.example.sharbat.data.db.model.EventModel


class EventAdapter(
    val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val items = mutableListOf<EventModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false), onItemClicked
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.updateModel(items[position])
    }

//    fun update(newItems: List<EventModel>) {
//        val result = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems) { zoneId })
//
//        items.clear()
//        items.addAll(newItems)
//        result.dispatchUpdatesTo(this)
//    }

    inner class EventViewHolder(
        itemView: View,
        val onItemClicked: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private var model: EventModel? = null

        init {
            itemView.setOnClickListener {
                model?.let { model ->
                    onItemClicked(model.id)
                }
            }
        }

        fun updateModel(newModel: EventModel) {
            model = newModel
            onModelUpdated()
        }

        private fun onModelUpdated() {
            model?.let { model ->
                itemView.apply {

                }
            }
        }
    }
}