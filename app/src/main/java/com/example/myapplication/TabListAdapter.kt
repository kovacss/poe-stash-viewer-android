package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.StashTab

class TabListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nameText: TextView = view.findViewById(R.id.textView)
    private val typeText: TextView = view.findViewById(R.id.textView2)


    fun bind(stashTab: StashTab) {
        nameText.text = stashTab.name
        typeText.text = stashTab.type

        this.itemView.setBackgroundColor(Color.parseColor(stashTab.color))
    }
}

class TabListAdapter(private val clickHandler: (StashTab) -> Unit) : ListAdapter<StashTab, TabListViewHolder>(DIFF_CONFIG) {

    companion object {
        val DIFF_CONFIG = object: DiffUtil.ItemCallback<StashTab>() {
            override fun areItemsTheSame(oldItem: StashTab, newItem: StashTab): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: StashTab, newItem: StashTab): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stash_tab, parent, false)
        return TabListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TabListViewHolder, position: Int) {
        val item = getItem(position);

        holder.bind(item)
        holder.itemView.setOnClickListener {
            this.clickHandler(item)
        }
    }
}