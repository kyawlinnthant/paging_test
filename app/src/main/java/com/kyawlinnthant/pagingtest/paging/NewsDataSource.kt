package com.kyawlinnthant.pagingtest.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.kyawlinnthant.pagingtest.data.DataManager
import com.kyawlinnthant.pagingtest.model.db.News
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsDataSource(
    private val job: Job,
    private val dataManager: DataManager
) : PageKeyedDataSource<Int, News>(), CoroutineScope {

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO
    private var PAGE = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {

        launch {
            try {
                val networkResult = dataManager.getKey(PAGE).await()
                if (networkResult.isSuccessful) {
                    callback.onResult(networkResult.body()?.articles!!, null, PAGE + 1)
                }else Log.d(this@NewsDataSource::class.java.simpleName,networkResult.errorBody().toString())
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {

                    Log.d(this@NewsDataSource::class.java.simpleName,e.toString())
                }
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

        launch {
            try {
                val networkResult = dataManager.getKey(params.key).await()
                if (networkResult.isSuccessful) {
                    callback.onResult(networkResult.body()?.articles!!,  params.key + 1)
                }else Log.d(this@NewsDataSource::class.java.simpleName,networkResult.errorBody().toString())
            } catch (e: Exception) {
                Log.d(this@NewsDataSource::class.java.simpleName,e.toString())
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }
}