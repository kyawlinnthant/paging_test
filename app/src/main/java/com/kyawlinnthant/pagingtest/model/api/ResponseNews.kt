package com.kyawlinnthant.pagingtest.model.api

import com.kyawlinnthant.pagingtest.model.db.News

data class ResponseNews (
    val articles : List<News>
)