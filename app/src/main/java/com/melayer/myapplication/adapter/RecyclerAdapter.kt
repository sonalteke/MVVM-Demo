package com.melayer.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.melayer.myapplication.R
import com.melayer.myapplication.models.NicePlaces
import de.hdodenhof.circleimageview.CircleImageView

import java.util.ArrayList

class RecyclerAdapter(private val mContext: Context, nicePlaces: List<NicePlaces>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mNicePlaces = ArrayList<NicePlaces>()

    init {
        mNicePlaces = nicePlaces as ArrayList<NicePlaces>
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {

        // Set the name of the 'NicePlace'
        (viewHolder as ViewHolder).mName.text = mNicePlaces[i].title

        // Set the image
        val defaultOptions = RequestOptions()
            .error(R.drawable.ic_launcher_background)
        Glide.with(mContext)
            .setDefaultRequestOptions(defaultOptions)
            .load(mNicePlaces[i].imageUrl)
            .into(viewHolder.mImage)
    }

    override fun getItemCount(): Int {
        return mNicePlaces.size
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImage: CircleImageView = itemView.findViewById(R.id.imgPlace)
        val mName: TextView = itemView.findViewById(R.id.txtPlaceName)

    }
}