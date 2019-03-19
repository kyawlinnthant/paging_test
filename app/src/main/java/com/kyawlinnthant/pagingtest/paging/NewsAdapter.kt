package com.kyawlinnthant.pagingtest.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kyawlinnthant.pagingtest.R
import com.kyawlinnthant.pagingtest.model.db.News

class NewsAdapter(
    private val onClick : (Int)->Unit
) : PagedListAdapter<News,RecyclerView.ViewHolder>(newsDiffUtil){


    companion object {
        val newsDiffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false),onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(getItem(position))
    }
}