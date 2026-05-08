package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ChooseInterestRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ChooseInterestRepository {

    val firebaseCollection = firestore.collection(FirebaseConstants.userCollection)

    override suspend fun addTags(tags: List<String>): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(AppErrors.userNotFound))
                }
                return@suspendCancellableCoroutine
            }

            firebaseCollection.whereEqualTo("userId", user.uid).get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val ref = querySnapshot.documents[0].reference.path

                        firestore.document(ref).update(
                            "tags", tags
                        ).addOnSuccessListener {
                            if (continuation.isActive) {
                                continuation.resume(ContentState.Success(Unit))
                            }
                        }
                            .addOnFailureListener {
                                if (continuation.isActive) {
                                    continuation.resume(
                                        ContentState.Error(it.message ?: AppErrors.unknownError)
                                    )
                                }
                            }

                    } else {
                        firebaseCollection.add(
                            hashMapOf(
                                "userId" to user.uid,
                                "tags" to tags,
                                "email" to user.email,
                                "fullName" to "",
                                "gender" to "",
                                "nickname" to "",
                                "phoneNumber" to "",
                                "pin" to "",
                                "profileUri" to "",
                            )
                        ).addOnSuccessListener {
                            if (continuation.isActive) {
                                continuation.resume(ContentState.Success(Unit))
                            }
                        }.addOnFailureListener { e ->
                            if (continuation.isActive) {
                                continuation.resume(
                                    ContentState.Error(e.message ?: AppErrors.unknownError)
                                )
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    if (continuation.isActive) {
                        continuation.resume(
                            ContentState.Error(e.message ?: AppErrors.unknownError)
                        )
                    }
                }
        }
}