package com.kyawlinnthant.pagingtest.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kyawlinnthant.pagingtest.app.AppConstant
import com.kyawlinnthant.pagingtest.data.AppDataManager
import com.kyawlinnthant.pagingtest.data.DataManager
import com.kyawlinnthant.pagingtest.data.db.AppDbHelper
import com.kyawlinnthant.pagingtest.data.db.DbHelper
import com.kyawlinnthant.pagingtest.data.db.room.NewsDatabase
import com.kyawlinnthant.pagingtest.data.remote.ApiHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single { getClient() }
    single { getApiService<ApiHelper>(get()) }
    single { AppDataManager(get()) as DataManager }
    single { AppDbHelper(getDatabase(androidContext(),"news")) as DbHelper }
}

fun getClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

inline fun <reified T> getApiService(client: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.API_END_POINT)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}

fun getDatabase(context: Context, name : String) = Room.databaseBuilder(context,NewsDatabase::class.java,name).build()
