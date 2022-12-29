package com.brokenbrains.fitness.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val authRepository: AuthRepository
) {

//    var allActivities = listOf<ActivityModel>()

    fun addNewActivity(
        activityModel: ActivityModel
    ) {
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("activities")
            .add(activityModel).addOnSuccessListener { documentReference ->
                Log.d(
                    this::class.simpleName,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
            }.addOnFailureListener { e ->
                Log.w(this::class.simpleName, "Error adding document $e")
            }
    }

    suspend fun getActivityById(id: Int): Flow<ActivityModel> {
        var activityModel = ActivityModel()
        return try {
            flow {
                db.collection("users")
                    .document(authRepository.currentUser!!.uid)
                    .collection("activities")
                    .document(id.toString())
                    .get()
                    .addOnSuccessListener { document ->
                        activityModel = document.toObject(ActivityModel::class.java)!!
                    }
                emit(activityModel)
            }
        } catch (e: Exception) {
            flow {
                emit(activityModel)
            }
        }
    }


    @WorkerThread
    fun getAllActivities(): Flow<List<ActivityModel>> = callbackFlow {
        val activities = mutableListOf<ActivityModel>()
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("activities").orderBy("startAt")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    activities.add(document.toObject(ActivityModel::class.java))
                }
                Log.d(this::class.simpleName, "DocumentSnapshot added with ID: $activities")
                trySend(activities).isSuccess
            }.addOnFailureListener { e ->
                Log.w(this::class.simpleName, "Error getting documents $e")
                cancel()
            }
        try {
            awaitClose {
                channel.close()
            }
        } catch (e: Exception) {
            Log.w(this::class.simpleName, "Error getting documents $e")
            cancel()
        }
    }


    fun getActivityByType(type: ActivityType): List<ActivityModel> {
        return listOf()
    }


}