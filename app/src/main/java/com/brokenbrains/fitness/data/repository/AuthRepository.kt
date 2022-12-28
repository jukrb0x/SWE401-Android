package com.brokenbrains.fitness.data.repository

import com.brokenbrains.fitness.network.ResultData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface IAuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): ResultData<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): ResultData<FirebaseUser>
    fun logout()

}

/**
 * Firebase Auth repository
 */
@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): ResultData<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            ResultData.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Failed(e.message)
        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): ResultData<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            )?.await()
            return ResultData.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Failed(e.message)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }


}