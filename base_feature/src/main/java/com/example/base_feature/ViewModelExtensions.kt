package com.example.base_feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

fun <T> ViewModel.viewState() = lazy{
    MutableLiveData<ViewState<T>>()
}