package com.example.crocusoft_mova.data.repository

import android.util.Log
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.models.ProfileUiModel
import com.example.crocusoft_mova.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume

class ProfileRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val fireStore : FirebaseFirestore) :
    ProfileRepository {
    override suspend fun logOut(): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->
            try {
                firebaseAuth.signOut()
                continuation.resume(ContentState.Success(Unit))

            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
                }
            }
        }

    override fun getCurrentUser(): Flow<ContentState<ProfileUiModel>> = callbackFlow {

        val user = firebaseAuth.currentUser
        if (user == null) {
            trySend(ContentState.Error(AppErrors.userNotFound))
            close()
            return@callbackFlow
        }

        val listener = fireStore.collection(FirebaseConstants.userCollection)
            .whereEqualTo("userId", user.uid)
            .addSnapshotListener { snapshots, exception ->

                if (exception != null) {
                    trySend(ContentState.Error(exception.message ?: AppErrors.unknownError))
                    return@addSnapshotListener
                }

                val document = snapshots?.documents?.firstOrNull()

                if (document == null) {
                    trySend(ContentState.Error(AppErrors.unknownError))
                    return@addSnapshotListener
                }

                val profile = ProfileUiModel(
                    profileId = user.uid,
                    email = user.email ?: "",
                    fullName = document.getString("fullName") ?: "",
                    nickName = document.getString("nickname") ?: "",
                    phoneNumber = document.getString("phoneNumber") ?: "",
                    gender = document.getString("gender") ?: "",
                    imageUri = document.getString("profileUri") ?: ""
                )

                trySend(ContentState.Success(profile))
            }

        awaitClose {
            listener.remove()
        }
    }

}