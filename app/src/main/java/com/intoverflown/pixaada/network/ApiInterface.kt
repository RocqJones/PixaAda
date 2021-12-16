package com.intoverflown.pixaada.network

import com.intoverflown.pixaada.data.DataImage
import com.intoverflown.pixaada.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    /**
     * parse api param query and call a data response model
     */
    @GET("?key=${Constants.API_key}&q=")
    suspend fun fetchSearchData(@Query("searchStr") searchStr : String): Response<DataImage>
}
