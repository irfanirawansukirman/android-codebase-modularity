package com.irfanirawansukirman.medsocauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class FacebookAuthUtil {

    private lateinit var callbackManager: CallbackManager

    fun setupFacebookCallback(
        callbackManager: CallbackManager
    ) {
        this.callbackManager = callbackManager
    }

    fun logout(onSuccessLogout: (Boolean) -> Unit) {
        LoginManager.getInstance().logOut()
        onSuccessLogout(true)
    }

    fun login(
        context: AppCompatActivity,
        onSuccessLogin: (MedsocResponse) -> Unit,
        onFailedLogin: (String) -> Unit
    ) {
        LoginManager.getInstance().apply {
            logInWithReadPermissions(context, listOf("email"))
            registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    val accessToken = result?.accessToken
                    accessToken?.let {
                        val request = GraphRequest.newMeRequest(
                            it
                        ) { obj, response ->
                            obj?.let { jsonObj ->
                                val id = jsonObj.getString("id")
                                val name = jsonObj.getString("name")
                                val email = jsonObj.getString("email")
                                val birthday = jsonObj.getString("birthday")
                                val avatar =
                                    "https://graph.facebook.com/$id/picture?type=large"
                                onSuccessLogin(
                                    MedsocResponse(
                                        id,
                                        name,
                                        email,
                                        birthday,
                                        avatar
                                    )
                                )
                            }
                        }

                        val parameters = Bundle()
                        parameters.putString("fields", "id,name,email,birthday")
                        request?.apply {
                            setParameters(parameters)
                            executeAsync()
                        }
                    }
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {
                    error?.message?.let {
                        onFailedLogin(it)
                    }
                }
            })
        }
    }

}