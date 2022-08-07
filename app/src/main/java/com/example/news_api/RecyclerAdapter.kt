package com.example.news_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter : RecyclerView.Adapter <RecyclerAdapter.ViewHolder>() {

    private val items = ArrayList<News>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleView : TextView = itemView.findViewById(R.id.title_card)
        //val author : TextView = itemView.findViewById(R.id.)
        val image : TextView = itemView.findViewById(R.id.img_card)
        //val titleView : TextView = itemView.findViewById(R.id.title_card)

        init {



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {


        val viewCardLayout = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(viewCardLayout)



    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        val positionNews = items[position]
        holder.titleView.text = positionNews.title


    }

    override fun getItemCount(): Int {

        return items.size
    }

    fun updateNews(newData:ArrayList<News>)
    {
        items.clear()
        items.addAll(newData)

        notifyDataSetChanged()
    }
}