package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.StashTabItem
import com.squareup.picasso.Picasso

class ItemListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val itemNameText: TextView = view.findViewById(R.id.itemNameTextView)
    private val itemLevelText: TextView = view.findViewById(R.id.itemLevelTextView)
    private val itemIcon: ImageView = view.findViewById(R.id.itemIcon)


    fun bind(item: StashTabItem) {
        itemNameText.text = item.name
        itemLevelText.text = item.itemLevel.toString()

        Picasso.get().load(item.iconUri).into(itemIcon);
    }
}

class ItemListAdapter() : ListAdapter<StashTabItem, ItemListViewHolder>(DIFF_CONFIG) {

    companion object {
        val DIFF_CONFIG = object: DiffUtil.ItemCallback<StashTabItem>() {
            override fun areItemsTheSame(oldItem: StashTabItem, newItem: StashTabItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: StashTabItem, newItem: StashTabItem): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stash_item, parent, false)
        return ItemListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val item = getItem(position);

        holder.bind(item)
//        holder.itemView.setOnClickListener {
//            this.clickHandler(item)
//        }
    }
}