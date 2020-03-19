package com.irfanirawansukirman.data.common.util

import android.content.Context
import com.irfanirawansukirman.data.common.ext.checkNetworkStatus

class ConnectivityImpl(private val context: Context) : Connectivity {

    override fun isNetworkAvailable() = checkNetworkStatus(context)

}