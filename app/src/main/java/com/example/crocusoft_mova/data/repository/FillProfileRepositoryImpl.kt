package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.repository.FillProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FillProfileRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FillProfileRepository {

    val collection = firestore.collection(FirebaseConstants.userCollection)

    override suspend fun fillProfile(
        fullName: String,
        gender: String,
        nickname: String,
        phoneNumber: String,
        profileUri: String
    ): ContentState<Unit> = suspendCancellableCoroutine { continuation ->

        try {
            firebaseAuth.currentUser?.let { user ->

                collection.whereEqualTo("userId", user.uid).get()
                    .addOnSuccessListener { snapshots ->
                        val ref = snapshots.documents[0].reference.path
                        firestore.document(ref).update(
                            "fullName", fullName,
                            "gender", gender,
                            "nickname", nickname,
                            "phoneNumber", phoneNumber,
                            "profileUri", profileUri
                        ).addOnSuccessListener {
                            continuation.resume(ContentState.Success(Unit))
                        }.addOnFailureListener {
                            continuation.resume(
                                ContentState.Error(
                                    it.message ?: AppErrors.unknownError
                                )
                            )
                        }

                    }

            }
        } catch (e: Exception) {
            if (continuation.isActive) {
                continuation.resume(ContentState.Error(e.message ?: "Unknown Error"))
            }
        }
    }

}