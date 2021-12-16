package com.intoverflown.pixaada.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intoverflown.pixaada.data.DataImage
import com.intoverflown.pixaada.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private var repository: Repository) : ViewModel() {

    val myHitResponse : MutableLiveData<Response<DataImage>> = MutableLiveData()

    /**
     * use coroutines concurrency design pattern which is asynchronously to manage long-running tasks
     * Search string as param
     */
    fun fetchHitsData(searchStr: String) {
        viewModelScope.launch {
            val response = repository.fetchHitsData(searchStr)
            myHitResponse.value = response
            Log.d("resViewModel", response.toString())
        }
    }
}