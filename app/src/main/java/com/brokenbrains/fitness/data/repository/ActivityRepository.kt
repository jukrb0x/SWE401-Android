package com.brokenbrains.fitness.data.repository

import android.util.Log
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val authRepository: AuthRepository
) {

    fun addNewActivity(
        activityModel: ActivityModel
    ) {
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("activities")
            .add(activityModel).addOnSuccessListener { documentReference ->
                Log.d(this::class.simpleName, "DocumentSnapshot added with ID: ${documentReference.id}")
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

    fun getAllActivities(): Flow<List<ActivityModel>> {
        val activities = mutableListOf<ActivityModel>()
        return flow {
            db.collection("users")
                .document(authRepository.currentUser!!.uid)
                .collection("activities")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        activities.add(document.toObject(ActivityModel::class.java))
                    }
                }
            emit(activities)
        }.flowOn(Dispatchers.IO)
    }

    fun getActivityByType(type: ActivityType): List<ActivityModel> {
        return listOf()
    }


}