package com.example.myapplication.UI

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DATA.Item
import com.example.myapplication.databinding.ItemLayoutBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ResponseAdapter(private val context: Context, private val responseList : List<Item>) : RecyclerView.Adapter<ResponseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        val itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ResponseViewHolder(itemLayoutBinding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder:ResponseViewHolder, pos: Int) {
        val data = responseList[pos]
        holder.itemLayoutBinding.apply {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            try {
                val time: Long = sdf.parse(data.snippet.publishedAt).time
                val now = System.currentTimeMillis()
                val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                publishedTime.text = ago
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            videoTitle.text = data.snippet.title
            channelName.text = data.snippet.channelTitle
            Glide.with(context).load(data.snippet.thumbnails.high.url).into(videoThumbnail)
            Glide.with(context).load(data.snippet.thumbnails.high.url).into(channelPicture)
        }
        holder.itemLayoutBinding.videoThumbnail.setOnClickListener { view: View? ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/watch?v=${data.id.videoId}")
            intent.setPackage("com.google.android.youtube")
            context.startActivity(intent)
        }
        holder.itemLayoutBinding.imageView10.setOnClickListener {
            var link = "https://www.youtube.com/watch?v=${data.id.videoId}"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return responseList.size
    }
}
class ResponseViewHolder(val itemLayoutBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemLayoutBinding.root)