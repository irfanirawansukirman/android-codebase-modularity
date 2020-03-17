package com.irfanirawansukirman.codebasemodularity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
            if (resultCode == 4321) {
                Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val movieListUri = "movielist://movielist/".toUri()
            val intent = Intent(Intent.ACTION_VIEW, movieListUri)
            startActivityForResult(intent, 1234)
        }, 1000)
    }

}
