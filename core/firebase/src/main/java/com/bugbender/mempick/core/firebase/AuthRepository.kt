package com.bugbender.mempick.core.firebase

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.bugbender.memepick.core.data.ProvideResources
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface AuthRepository {

    fun isUserLogged(): Boolean

    interface LoginWithGoogle {

        suspend fun loginWithGoogle(context: Context): AuthResult
    }

    fun userEmail(): String

    fun signOut()

    class Base @Inject constructor(
        private val provideResources: ProvideResources,
    ) : AuthRepository, LoginWithGoogle {

        private val auth: FirebaseAuth = Firebase.auth

        override fun isUserLogged() = auth.currentUser != null

        override suspend fun loginWithGoogle(context: Context): AuthResult {
            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(provideResources.stringById(R.string.web_client_id))
                .setAutoSelectEnabled(true)
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credentialManager = CredentialManager.create(context)

            return try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                when (val credential = result.credential) {
                    is CustomCredential -> {
                        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)

                            val authCredential = GoogleAuthProvider.getCredential(
                                googleIdTokenCredential.idToken,
                                null
                            )

                            auth.signInWithCredential(authCredential).await()

                            AuthResult.Success

                        } else {
                            AuthResult.Error(message = "Unexpected type of credential")
                        }
                    }

                    else -> {
                        AuthResult.Error(message = "Unexpected type of credential")
                    }
                }
            } catch (e: GetCredentialException) {
                AuthResult.Error(message = e.message ?: "Something went wrong")
            }
        }

        override fun userEmail() =
            auth.currentUser?.email ?: provideResources.stringById(R.string.no_email_address)

        override fun signOut() = auth.signOut()
    }
}