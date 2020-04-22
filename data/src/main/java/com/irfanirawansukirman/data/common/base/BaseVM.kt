package com.irfanirawansukirman.data.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.data.common.coroutine.CoroutineContextProvider
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

abstract class BaseVM(private val coroutineContextProvider: CoroutineContextProvider) :
    ViewModel() {

    fun executeCase(
        execute: suspend () -> Unit,
        timeOutException: (Throwable) -> Unit,
        errorException: (Throwable) -> Unit
    ) {
        viewModelScope.launch(coroutineContextProvider.main) {
            try {
                // 15 second
                withTimeout(15000) {
                    execute()
                }
            } catch (e: TimeoutCancellationException) {
                timeOutException(Throwable("Request Timeout")) // 408 for error code
            } catch (e: Exception) {
                errorException(Throwable(e.message ?: "Something Went Wrong")) // 500 for error code
            }
        }
    }
}