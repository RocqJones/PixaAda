package com.intoverflown.pixaada.ui.detailsview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.intoverflown.pixaada.R
import com.intoverflown.pixaada.databinding.ActivityDetailsBinding
import com.intoverflown.pixaada.ui.dashboard.MainActivity

class DetailsActivity : AppCompatActivity() {

    private var binding : ActivityDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        /**
         * Get data from intents
         */
        val imgUrl = intent.getStringExtra("largeImageURL")
        val imgViews = intent.getStringExtra("views")
        val userName = intent.getStringExtra("user")
        val tags = intent.getStringExtra("tags")

        /**
         * Up button
         */
        binding!!.backBtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()
        }

        /**
         * Set data to details screen
         */
        binding!!.tagName.text = tags
        binding!!.noOfViews.text = imgViews
        binding!!.userName.text = userName

        Glide.with(binding!!.largeImg.context).load(imgUrl)
            .apply(
                RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)).into(binding!!.largeImg)
    }
}