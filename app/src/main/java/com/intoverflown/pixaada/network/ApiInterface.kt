package com.intoverflown.pixaada.network

import com.intoverflown.pixaada.data.DataImage
import com.intoverflown.pixaada.ui.dashboard.MainActivity
import com.intoverflown.pixaada.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    val searchString: String

    /**
     * parse api param query and call a data response model
     */

    @GET("?key=${Constants.API_key}&q=dogs")
//    Call<SearchData>
    suspend fun fetchSearchData(): Response<DataImage>



}
