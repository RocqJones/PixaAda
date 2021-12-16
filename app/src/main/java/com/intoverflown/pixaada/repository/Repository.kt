package com.intoverflown.pixaada.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.intoverflown.pixaada.data.DataImage
import com.intoverflown.pixaada.data.SearchData
import com.intoverflown.pixaada.network.ApiInterface
import com.intoverflown.pixaada.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    /**
     * Fetch response from api
     * Parse search string as param
      */
    suspend fun fetchHitsData(searchStr: String) : Response<DataImage> {
        Log.d("resRepository", RetrofitInstance.api.fetchSearchData(searchStr).toString())
        return RetrofitInstance.api.fetchSearchData(searchStr)
    }
}