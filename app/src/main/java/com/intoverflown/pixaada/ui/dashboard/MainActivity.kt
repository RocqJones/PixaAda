package com.intoverflown.pixaada.ui.dashboard

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.intoverflown.pixaada.R
import com.intoverflown.pixaada.databinding.ActivityMainBinding
import com.intoverflown.pixaada.repository.Repository
import com.intoverflown.pixaada.ui.viewmodel.ViewModel
import com.intoverflown.pixaada.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.secTwoTxt.text = getString(R.string.popular_images) + " " + getString(R.string.popular_list)

        // initialize ViewModels
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]

        displayData()
    }

    private fun displayData() {
        viewModel.fetchHitsData()
        viewModel.myHitResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.toString()?.let { Log.d("response", it) }
                Log.d("responseTotalRes", response.body()?.total.toString())
                Log.d("responseTotalHits", response.body()?.totalHits.toString())
                Log.d("responseHits", response.body()?.hits.toString())

                binding!!.totalSearchResults.text = "Found +" + response.body()?.total.toString() + " results"
                binding!!.totalSearchResults.paintFlags = binding!!.totalSearchResults.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            } else {
                // Response API Error
                Log.e("responseError: ", response.errorBody().toString())
            }
        })
    }
}