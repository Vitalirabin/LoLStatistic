package com.example.lolstatistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFactory<T : ViewModel>(private val classVM: Class<T>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        validateClass(modelClass)
        return createViewModel() as T
    }

    private fun <T : ViewModel?> validateClass(modelClass: Class<T>) {
        if (!modelClass.isAssignableFrom(classVM)) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    abstract fun createViewModel(): T
}