package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.StashTab
import com.example.myapplication.model.StashTabItem
import com.example.myapplication.repository.ItemRepository

class StashTabActivity : AppCompatActivity() {

    private val itemRepository = ItemRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stash_tab)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        initStashTabItemList();
    }

    private fun initStashTabItemList() {
        val stashTabList: RecyclerView = findViewById(R.id.ItemRecyclerView);
        stashTabList.layoutManager = LinearLayoutManager(this)
        val adapter = ItemListAdapter()
        stashTabList.adapter = adapter

        val stashTabObserver = Observer<List<StashTabItem>> { stashTabItems ->
            // Update our List adapter
            println("loaded ${stashTabItems.size} 'StashTabItem'");

            adapter.submitList(stashTabItems)
        }


        itemRepository.stashTabs.observe(this, stashTabObserver);

        itemRepository.load();
    }
}