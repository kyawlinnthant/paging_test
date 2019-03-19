package com.kyawlinnthant.pagingtest.data

import com.kyawlinnthant.pagingtest.app.AppConstant
import com.kyawlinnthant.pagingtest.data.remote.ApiHelper
import com.kyawlinnthant.pagingtest.model.api.ResponseNews
import kotlinx.coroutines.Deferred
import retrofit2.Response

open class AppDataManager(
    private val apiHelper: ApiHelper
) : DataManager, ApiHelper by apiHelper{

    override fun getKey(page : Int): Deferred<Response<ResponseNews>> = getData(AppConstant.API_KEY,"technology",page)
}