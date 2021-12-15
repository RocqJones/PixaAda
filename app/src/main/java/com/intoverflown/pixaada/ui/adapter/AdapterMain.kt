package com.intoverflown.pixaada.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.intoverflown.pixaada.R
import com.intoverflown.pixaada.data.DataList
import com.intoverflown.pixaada.databinding.ItemGridViewBinding
import com.intoverflown.pixaada.ui.detailsview.DetailsActivity


class AdapterMain(var rvData: List<DataList>, context: Context) :
    RecyclerView.Adapter<AdapterMain.RVHolder>() {
    var binding: ItemGridViewBinding? = null
    var mContext: Context? = context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RVHolder {
        binding = ItemGridViewBinding.inflate(LayoutInflater.from(viewGroup.context))
        val view: View = binding!!.root
        return RVHolder(view)
    }

    override fun onBindViewHolder(holder: RVHolder, i: Int) {
        /**
         * I'm using Glide library to load an image by URL into an [ImageView]
         * override method reduces the pixels of an image to fit the screen
         * Then placeholder anim and error img sample in cases where the img link is broken
         */
        rvData[i].previewURL?.let { Log.d("resAdapterD", it) }

        if (rvData[i].previewURL != null) {
            val url = rvData[i].previewURL
            Glide.with(holder.previewImg.context).load(url).override(180, 150)
                .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
                .into(holder.previewImg)
        } else {
            Glide.with(holder.previewImg.context).load(R.drawable.ic_broken_image)
                .override(180, 150)
                .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
                .into(holder.previewImg)
        }

        /**
         * intent to Details activity with more user details
         */
        val largeImageURL: String? = rvData[i].largeImageURL
        val user: String? = rvData[i].user
        val views: String? = rvData[i].views
        holder.previewImg.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("largeImageURL", largeImageURL)
            intent.putExtra("user", user)
            intent.putExtra("views", views)
            mContext!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return rvData.size
    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var previewImg: ImageView

        init {
            val itemGridViewBinding: ItemGridViewBinding = ItemGridViewBinding.bind(itemView)
            previewImg = itemGridViewBinding.previewImage
        }
    }

}