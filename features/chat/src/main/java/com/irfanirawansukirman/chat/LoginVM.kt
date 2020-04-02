package com.irfanirawansukirman.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginVM: ViewModel() {
    val inputNumber = MutableLiveData<Int>()
    val outputNumber = MutableLiveData<Int>()
}