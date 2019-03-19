package com.kyawlinnthant.pagingtest.ui

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kyawlinnthant.pagingtest.data.DataManager
import com.kyawlinnthant.pagingtest.paging.NewsDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

class MainViewModel(dataManager: DataManager) : ViewModel(),CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    private val config = PagedList.Config.Builder().setPageSize(25).build()

    val data  = LivePagedListBuilder(NewsDataSourceFactory(job,dataManager),config).build()

    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
    }

}