package com.example.sharbat.domain

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.sharbat.R

class EventsAdapter(
    val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private val items = mutableListOf<EventViewState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(parent.inflate(R.layout.list_item_zone), onItemClicked)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.updateModel(items[position])
    }

    fun update(newItems: List<EventViewState>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems) { })
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    inner class EventViewHolder(itemView: View, val onItemClicked: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var model: EventViewState? = null
        //private val emptyDescription by lazy { getString(R.string.activity_choose_zone_empty_description) }

        init {
            itemView.setOnClickListener {
                model?.let { model ->
                    onItemClicked(model.id) //todo
                }
            }
        }

        fun updateModel(newModel: EventViewState) {
            model = newModel
            onModelUpdated()
        }

        private fun onModelUpdated() {
            model?.let { model ->
                itemView.apply {
                    //                    val warehouseDescription = model.warehouseDescription ?: emptyDescription
//                    val zoneDescription = model.description ?: emptyDescription
//                    textViewZoneDescription.text = if (model.showWarehouse) {
//                        "$warehouseDescription / $zoneDescription"
//                    } else {
//                        zoneDescription
//                    }
                    //todo
                }
            }
        }
    }
}