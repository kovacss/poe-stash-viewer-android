package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.StashTab
import com.example.myapplication.repository.StashTabRepository

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val repository =
        StashTabRepository();

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStashTabList(view)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            println("Item Clicked")
            repository.load();
        }
    }

    private fun initStashTabList(view: View) {
        val stashTabList: RecyclerView = view.findViewById(R.id.recyclierView);
        stashTabList.layoutManager = LinearLayoutManager(activity)
        val adapter = TabListAdapter(::onItemSelected)
        stashTabList.adapter = adapter

        val stashTabObserver = Observer<List<StashTab>> {stashTabItems ->
            // Update our List adapter
            println("loaded${stashTabItems.size}");

            adapter.submitList(stashTabItems)
            Toast.makeText(activity?.applicationContext, "List${stashTabItems.size}", Toast.LENGTH_LONG).show()
        }

        repository.stashTabs.observe(viewLifecycleOwner, stashTabObserver);
    }

    fun onItemSelected(item: StashTab) {
        TODO("StashTab View not implemented")
    }





}