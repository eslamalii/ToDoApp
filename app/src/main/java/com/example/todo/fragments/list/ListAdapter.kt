package com.example.todo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.models.Priority
import com.example.todo.data.models.ToDoData

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var dataItems = emptyList<ToDoData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title_txt).text = dataItems[position].title
        holder.itemView.findViewById<TextView>(R.id.description_txt).text =
            dataItems[position].description

        holder.itemView.findViewById<ConstraintLayout>(R.id.row_background).setOnClickListener {
            val action =
                ListFragmentDirections.actionListFragmentToUpdateFragment(dataItems[position])
            holder.itemView.findNavController().navigate(action)
        }

        when (dataItems[position].priority) {
            Priority.HIGH -> holder.itemView.findViewById<CardView>(R.id.priority_indicator)
                .setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.red
                    )
                )
            Priority.MEDIUM -> holder.itemView.findViewById<CardView>(R.id.priority_indicator)
                .setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.yellow
                    )
                )
            Priority.LOW -> holder.itemView.findViewById<CardView>(R.id.priority_indicator)
                .setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.green
                    )
                )

        }


    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)


    fun setData(data: List<ToDoData>) {
        this.dataItems = data
        notifyDataSetChanged()
    }
}