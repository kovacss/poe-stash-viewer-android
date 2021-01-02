package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.StashTab
import com.example.myapplication.model.dto.StashTabResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StashTabRepository {

    private val _stashTabs = MutableLiveData<List<StashTab>>();
    val stashTabs: LiveData<List<StashTab>> =_stashTabs;

    private val service: POEService = getPOEService()

    fun load() {
        val call = service.getStashTabs("standard", "mathil")
        call?.enqueue(object: Callback<StashTabResponse> {
            override fun onFailure(call: Call<StashTabResponse>, t: Throwable) {
                throw t
            }

            override fun onResponse(call: Call<StashTabResponse>, response: Response<StashTabResponse>) {
                println("Result ${response.code()}")

                if (response.isSuccessful) {
                    _stashTabs.value = response.body()?.tabs?.map {
                        StashTab(it.n, it.i, "something", "red")
                    }
//                    println(body?.numTabs);
//                    println(body?.tabs?.size);
                }
            }
        });

//        val randomValues = listOf(StashTab("T2 (Remove-only)", 0, "PremiumStash", "#FF0000"))
//        _stashTabs.value = randomValues;
    }

}