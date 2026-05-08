package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.repository.PinRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PinRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : PinRepository {

    val collection = firestore.collection(FirebaseConstants.userCollection)

    override suspend fun createPin(pin: String): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->
            try {

                val user = firebaseAuth.currentUser
                if (user == null) {
                    if (continuation.isActive) {
                        continuation.resume(ContentState.Error(AppErrors.userNotFound))
                    }
                    return@suspendCancellableCoroutine
                }

                collection.whereEqualTo("userId", user.uid).get()
                    .addOnSuccessListener { querySnapshot ->
                        val ref = querySnapshot.documents[0].reference.path

                        firestore.document(ref).update("pin", pin).addOnSuccessListener {
                            continuation.resume(ContentState.Success(Unit))
                        }.addOnFailureListener {
                            continuation.resume(
                                ContentState.Error(
                                    it.message ?: AppErrors.unknownError
                                )
                            )
                        }


                    }

            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
                }
            }
        }

    override suspend fun enterPin(pin: String): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->

            try {
                val user = firebaseAuth.currentUser
                if (user == null) {
                    if (continuation.isActive) continuation.resume(ContentState.Error(AppErrors.userNotFound))
                    return@suspendCancellableCoroutine
                }

                collection.whereEqualTo("userId", user.uid).get()
                    .addOnSuccessListener { querySnapshots ->
                        val snapshots = querySnapshots.documents[0]

                        if (snapshots.get("pin") == "") {

                            val ref = snapshots.reference.path
                            firestore.document(ref).update("pin", pin).addOnSuccessListener {
                                continuation.resume(ContentState.Success(Unit))

                            }
                                .addOnFailureListener {
                                    continuation.resume(
                                        ContentState.Error(
                                            it.message ?: AppErrors.unknownError
                                        )
                                    )
                                }
                            return@addOnSuccessListener
                        }
                        if (snapshots.get("pin") == pin) {
                            continuation.resume(ContentState.Success(Unit))

                        } else {
                            continuation.resume(ContentState.Error(AppErrors.wrongPin))
                        }
                    }.addOnFailureListener {
                        continuation.resume(
                            ContentState.Error(
                                it.message ?: AppErrors.unknownError
                            )
                        )
                    }


            } catch (e: Exception) {
                continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
            }
        }

}