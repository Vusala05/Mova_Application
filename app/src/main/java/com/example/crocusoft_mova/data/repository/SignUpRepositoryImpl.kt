package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignUpRepository {


    override suspend fun signUp(
        email: String,
        password: String
    ): ContentState<Unit> = suspendCancellableCoroutine { continuation ->
        if (email.isBlank() || password.isBlank()) {
            continuation.resume(ContentState.Error(AppErrors.emptyField))
            return@suspendCancellableCoroutine
        }
        try {
            firebaseAuth
                .createUserWithEmailAndPassword(email, password).addOnFailureListener {
                    if (continuation.isActive) {
                        continuation.resume(
                            ContentState.Error(
                                it.message ?: AppErrors.unknownError
                            )
                        )

                    }
                }
                .addOnSuccessListener {
                    if (continuation.isActive) {
                        continuation.resume(ContentState.Success(Unit))
                    }
                }


        } catch (e: Exception) {
            if (continuation.isActive) {
                continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
            }
        }
    }


}