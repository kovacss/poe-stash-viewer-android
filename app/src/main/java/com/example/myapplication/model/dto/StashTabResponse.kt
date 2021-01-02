package com.example.myapplication.model.dto

data class StashTabResponse(val numTabs: Int, val tabs: List<StashTabItemResponse>)

data class StashTabItemResponse(val n: String, val i: Int)