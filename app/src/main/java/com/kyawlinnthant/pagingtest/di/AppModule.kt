package com.kyawlinnthant.pagingtest.di

import com.kyawlinnthant.pagingtest.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel{MainViewModel(get())}
}