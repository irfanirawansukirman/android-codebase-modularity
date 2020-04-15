package com.irfanirawansukirman.medsocauth

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthUtil {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    fun setupGsoClient(context: AppCompatActivity, defaultWebClientId: String) {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(defaultWebClientId)
            .requestEmail()
            .build()

        if (!::googleSignInClient.isInitialized) {
            googleSignInClient = GoogleSignIn.getClient(context, gso)
        }

        if (!::auth.isInitialized) {
            auth = FirebaseAuth.getInstance()
        }
    }

    private fun getCurrentUser(): FirebaseUser? = auth.currentUser

    private fun getFirebaseAuth(): FirebaseAuth = auth

    fun login(context: AppCompatActivity, requestCode: Int) {
        val intent = googleSignInClient.signInIntent
        context.startActivityForResult(intent, requestCode)
    }

    fun logout(context: AppCompatActivity, onSuccessLogout: (Boolean) -> Unit) {
        auth.apply {
            signOut()

            googleSignInClient.apply {
                signOut().addOnCompleteListener(context) {
                    revokeAccess().addOnCompleteListener(context) {
                        onSuccessLogout(true)
                    }
                }
            }
        }
    }

    fun firebaseAuthWithGoogle(
        context: AppCompatActivity,
        acct: GoogleSignInAccount,
        onSuccessLogin: (MedsocResponse) -> Unit,
        onFailedLogin: (String) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        getFirebaseAuth().signInWithCredential(credential).addOnCompleteListener(context) { task ->
            if (task.isSuccessful) {
                getCurrentUser()?.let {
                    onSuccessLogin(
                        MedsocResponse(
                            it.uid,
                            it.displayName ?: "",
                            it.email ?: "",
                            "",
                            it.photoUrl.toString()
                        )
                    )
                }
            } else {
                task.exception?.message?.let {
                    onFailedLogin(it)
                }
            }
        }
    }

}