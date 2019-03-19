package com.kyawlinnthant.pagingtest.data

import com.kyawlinnthant.pagingtest.data.remote.ApiHelper
import com.kyawlinnthant.pagingtest.model.api.ResponseNews
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface DataManager : ApiHelper {

    fun getKey(page: Int): Deferred<Response<ResponseNews>>
}