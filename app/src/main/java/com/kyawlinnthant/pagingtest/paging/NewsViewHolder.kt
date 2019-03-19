package com.kyawlinnthant.pagingtest.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kyawlinnthant.pagingtest.R
import com.kyawlinnthant.pagingtest.model.db.News
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_layout.*

class NewsViewHolder(
    private val view : View,
    private val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view),LayoutContainer{

    private var news : News? = null
    override val containerView: View? get() = view

    init {
        this.viewClick().setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun bind(news: News?){
        this.news = news
        setupView(news)
    }
    private fun viewClick() = itemView
    private fun setupView(news: News?){
        title.text = news?.title
        news?.author?.let { author.text = it }
        description.text = news?.description
        publishedAt.text = news?.publishedAt
        Picasso.get().load(news?.urlToImage).resize(235,235).placeholder(R.drawable.ic_logo).into(urlToImage)
    }
}