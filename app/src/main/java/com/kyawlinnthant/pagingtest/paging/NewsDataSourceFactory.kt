package com.kyawlinnthant.pagingtest.paging

import androidx.paging.DataSource
import com.kyawlinnthant.pagingtest.data.DataManager
import com.kyawlinnthant.pagingtest.model.db.News
import kotlinx.coroutines.Job

class NewsDataSourceFactory(
    private val job: Job,
    private val dataManager: DataManager
): DataSource.Factory<Int, News>() {

    override fun create(): DataSource<Int, News> {
        return NewsDataSource(job,dataManager)
    }
}