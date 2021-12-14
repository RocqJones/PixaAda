package com.intoverflown.pixaada.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intoverflown.pixaada.R
import com.intoverflown.pixaada.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.secTwoTxt.text = getString(R.string.popular_images) + " " + getString(R.string.popular_list)
    }
}