package com.example.sharbat.presentation.features.events

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sharbat.R
import com.example.sharbat.presentation.utils.DiffUtilCallback
import com.example.sharbat.presentation.utils.inflate
import kotlinx.android.synthetic.main.item_event.view.*

class EventsAdapter(
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private val items = mutableListOf<EventViewState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = parent.inflate(R.layout.item_event)
        return EventViewHolder(view, onItemClicked)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.updateModel(items[position])
    }

    fun update(newItems: List<EventViewState>) {
        val result = DiffUtil.calculateDiff(
            DiffUtilCallback(
                items,
                newItems
            ) { })
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    inner class EventViewHolder(
        itemView: View,
        val onItemClicked: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var model: EventViewState? = null

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
                    textViewTitle.text = model.title
                    textViewDate.text = model.date
                    textViewTime.text = model.time
                    textViewPlace.text = model.place
                }
            }
        }
    }
}