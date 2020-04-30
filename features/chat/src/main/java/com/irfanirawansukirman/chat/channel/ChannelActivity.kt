package com.irfanirawansukirman.chat.channel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_CHAT
import com.irfanirawansukirman.chat.R

@DeepLink(TO_CHAT)
class ChannelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.channel_activity)
    }

}
