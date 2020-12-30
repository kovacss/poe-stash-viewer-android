package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.StashTab

class StashTabRepository {

    private val _stashTabs = MutableLiveData<List<StashTab>>();
    val stashTabs: LiveData<List<StashTab>> =_stashTabs;


    fun load() {
        val randomValues = listOf(StashTab("T2 (Remove-only)", 0, "PremiumStash", "#FF0000"))
        _stashTabs.value = randomValues;
    }

}