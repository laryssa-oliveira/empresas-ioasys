package com.example.empresas.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.presentation.ViewState

fun <T> ViewModel.viewState() = lazy{
    MutableLiveData<ViewState<T>>()
}