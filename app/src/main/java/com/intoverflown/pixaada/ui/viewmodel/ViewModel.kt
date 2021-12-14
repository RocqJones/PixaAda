package com.intoverflown.pixaada.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intoverflown.pixaada.data.DataImage
import com.intoverflown.pixaada.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private var repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<DataImage>> = MutableLiveData()

    fun fetchHitsData() {
        // use coroutines concurrency design pattern which is asynchronously to manage long-running tasks
        viewModelScope.launch {
            val response = repository.fetchHitsData()
            myResponse.value = response
            Log.d("resViewModel", response.toString())
        }
    }
}