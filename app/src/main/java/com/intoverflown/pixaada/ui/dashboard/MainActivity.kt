package com.intoverflown.pixaada.ui.dashboard

import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.intoverflown.pixaada.R
import com.intoverflown.pixaada.data.DataList
import com.intoverflown.pixaada.data.Hit
import com.intoverflown.pixaada.databinding.ActivityMainBinding
import com.intoverflown.pixaada.repository.Repository
import com.intoverflown.pixaada.ui.adapter.AdapterMain
import com.intoverflown.pixaada.ui.viewmodel.ViewModel
import com.intoverflown.pixaada.ui.viewmodel.ViewModelFactory
import java.util.*


class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var viewModel: ViewModel

    var adapterMain: AdapterMain? = null
    val list: ArrayList<DataList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.secTwoTxt.text = getString(R.string.popular_images) + " " + getString(R.string.popular_list)

        /**
         * init recyclerView
         */
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding!!.imgRecycler.layoutManager = gridLayoutManager
        binding!!.imgRecycler.adapter = adapterMain

        /**
         * initialize ViewModels
         */
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]

        displayData("dogs")
        /**
         * initialize search bar with [TextWatcher] on data change with Text Changed Listener
         */
        binding!!.searchString.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val searchString = binding!!.searchString.text.toString().trim()
                if (list.isNotEmpty()) {
                    list.clear()
                }
                Log.d("resSearchStr", searchString)
                displayData(searchString)
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun displayData(searchString: String) {
        viewModel.fetchHitsData(searchString)
        viewModel.myHitResponse.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.toString()?.let { Log.d("response", it) }
                Log.d("responseTotalRes", response.body()?.total.toString())
                Log.d("responseTotalHits", response.body()?.totalHits.toString())
                Log.d("responseHits", response.body()?.hits.toString())

//                Glide.with(binding!!.testGlide.context).load("https://cdn.pixabay.com/photo/2015/06/02/12/59/book-794978_150.jpg").into(binding!!.testGlide)

                val passToAdapter: List<Hit>? = response.body()?.hits?.toMutableList()
                Log.d("url", passToAdapter.toString())
                /**
                 * Iterate through Mutable JSONArray value of response [Hit] key
                 */
                for (i in passToAdapter!!.iterator()) {
                    val comment = i.comments
                    val previewURL = i.previewURL
                    val collections = i.collections
                    val id = i.id
                    val largeImageURL = i.largeImageURL
                    val likes = i.likes
                    val previewHeight = i.previewHeight
                    val tags = i.tags
                    val user = i.user
                    val userImageURL = i.userImageURL
                    val user_id = i.user_id
                    val views = i.views
                    Log.d("resM", "$comment : $previewURL")

                    val dList: DataList = DataList()
                    dList.collections = collections
                    dList.comments = comment
                    dList.id = id
                    dList.largeImageURL = largeImageURL
                    dList.likes = likes
                    dList.previewHeight = previewHeight
                    dList.previewURL = previewURL
                    dList.tags = tags
                    dList.user = user
                    dList.userImageURL = userImageURL
                    dList.user_id = user_id
                    dList.views = views
                    list.add(dList)
                }

                adapterMain = AdapterMain(list, this)
                binding?.imgRecycler?.adapter = adapterMain

                if (list.isNotEmpty()) {
                    binding!!.loader.visibility = View.GONE
                    binding!!.imgRecycler.visibility = View.VISIBLE
                } else {
                    binding!!.loader.visibility = View.VISIBLE
                }

                binding!!.totalSearchResults.text =
                    "Found +" + response.body()?.total.toString() + " results"
                binding!!.totalSearchResults.paintFlags =
                    binding!!.totalSearchResults.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            } else {
                // Response API Error
                Log.e("responseError: ", response.errorBody().toString())
            }
        }
    }
}