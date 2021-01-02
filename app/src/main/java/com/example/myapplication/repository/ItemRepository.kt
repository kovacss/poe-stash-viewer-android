package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.StashTabItem
import com.example.myapplication.model.dto.StashTabResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemRepository() {

    private val _stashTabs = MutableLiveData<List<StashTabItem>>();
    val stashTabs: LiveData<List<StashTabItem>> =_stashTabs;

    private val service: POEService = getPOEService()


    fun load() {


        val randomValues = listOf(StashTabItem("T2 (Remove-only)", 0, "https://web.poecdn.com/image/Art/2DItems/Currency/CurrencyRerollRare.png?scale=1&w=1&h=1"))
        _stashTabs.value = randomValues;
    }
}