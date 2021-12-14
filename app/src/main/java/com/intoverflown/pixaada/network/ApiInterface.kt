package com.intoverflown.pixaada.network

import com.intoverflown.pixaada.data.DataImage
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    // parse api param query and create a data response setters and getters
    @GET("&q=dogs")
    suspend fun fetchSearchData(): Response<DataImage>
}