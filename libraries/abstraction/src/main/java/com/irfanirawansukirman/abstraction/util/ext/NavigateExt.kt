package com.irfanirawansukirman.abstraction.util.ext

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : AppCompatActivity> navigate(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)

    //=========== How to using it ===========
    // navigator<YourActivity>()
    //=======================================
}

inline fun <reified T : AppCompatActivity> navigate(
    context: Context,
    intentParams: Intent.() -> Unit
) {
    val intent = Intent(context, T::class.java)
    intent.intentParams()
    context.startActivity(intent)

    //=========== How to using it ===========
    // navigator<DetailActivity> {
    //        putExtra("KEY1" , "VALUE1")
    //        putExtra("KEY2" , "VALUE2")
    //    }
    //=======================================
}