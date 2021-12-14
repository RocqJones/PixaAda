package com.intoverflown.pixaada.repository

import android.util.Log
import com.intoverflown.pixaada.data.DataImage
import com.intoverflown.pixaada.network.RetrofitInstance
import retrofit2.Response

class Repository {
    // Fetch response from api
    suspend fun fetchHitsData() : Response<DataImage> {
        Log.d("resRepository", RetrofitInstance.api.fetchSearchData().toString())
        return RetrofitInstance.api.fetchSearchData()
    }
}