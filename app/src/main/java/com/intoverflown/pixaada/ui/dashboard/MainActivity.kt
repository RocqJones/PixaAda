package com.intoverflown.pixaada.ui.dashboard

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.intoverflown.pixaada.R
import com.intoverflown.pixaada.databinding.ActivityMainBinding
import com.intoverflown.pixaada.repository.Repository
import com.intoverflown.pixaada.ui.viewmodel.ViewModel
import com.intoverflown.pixaada.ui.viewmodel.ViewModelFactory
import AdapterMain
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.intoverflown.pixaada.data.Hit
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var viewModel: ViewModel

    var adapterMain: AdapterMain? = null
    private var dataHits: List<Hit>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.secTwoTxt.text = getString(R.string.popular_images) + " " + getString(R.string.popular_list)

        // init recyclerView
        dataHits = ArrayList<Hit>()
        val linearLayoutManager = LinearLayoutManager(this)
        binding!!.imgRecycler.layoutManager = linearLayoutManager

        // initialize ViewModels
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]

        displayData()
    }

    private fun displayData() {
        viewModel.fetchHitsData()
        viewModel.myHitResponse.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.toString()?.let { Log.d("response", it) }
                Log.d("responseTotalRes", response.body()?.total.toString())
                Log.d("responseTotalHits", response.body()?.totalHits.toString())
                Log.d("responseHits", response.body()?.hits.toString())


//                val passToAdapter: String = response.body()?.hits?.indexOf(Hit(collections = getString()).collections)

                val passToAdapter: List<Hit>? = response.body()?.hits?.toMutableList()
                Log.d("url", passToAdapter.toString())
                for (i in passToAdapter!!.iterator()) {
                    val comment = i.comments
                    val previewURL = i.previewURL
                    Log.d("resM", "$comment : $previewURL")
                    Log.d("resDataHits", dataHits.toString())
//                    Glide.with(previewImg!!.context).load(previewURL).into(previewImg!!)

//                    val dHits = Hit(
//                        i.collections, i.comments, i.id, i.largeImageURL, i.likes, i.previewHeight,
//                        i.previewURL, i.tags, i.user, i.userImageURL, i.user_id, i.views
//                    )
                }

//                dataHits = response.body()?.hits.toString())
//                val jsonArray: String = jj
//                for (i in 0 until jsonArray.length()) {
//                    val jsonObject = jsonArray[i] as JSONObject
//                    Log.d("resArr", jsonObject.optString("largeImageURL"))
//                    val dHits = Hit(
//                        jsonObject.optString("collections"),
//                        jsonObject.optString("comments"),
//                        jsonObject.optString("id"),
//                        jsonObject.optString("largeImageURL"),
//                        jsonObject.optString("likes"),
//                        jsonObject.optString("previewHeight"),
//                        jsonObject.optString("previewURL"),
//                        jsonObject.optString("tags"),
//                        jsonObject.optString("user"),
//                        jsonObject.optString("userImageURL"),
//                        jsonObject.optString("user_id"),
//                        jsonObject.optString("views")
//                    )
////                    dataHits.add(dHits)
//                }
//                adapterMain = dataHits?.let { AdapterMain(it, this) }
//                binding!!.imgRecycler.adapter = adapterMain

//                val previewImg: ImageView = findViewById<View>(R.id.previewImage) as ImageView
//                if (dataHits!!.isEmpty()) {
//                    Glide.with(previewImg.context).load(R.drawable.ic_broken_image).into(previewImg)
//                }

                binding!!.totalSearchResults.text = "Found +" + response.body()?.total.toString() + " results"
                binding!!.totalSearchResults.paintFlags = binding!!.totalSearchResults.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            } else {
                // Response API Error
                Log.e("responseError: ", response.errorBody().toString())
            }
        })
    }
}