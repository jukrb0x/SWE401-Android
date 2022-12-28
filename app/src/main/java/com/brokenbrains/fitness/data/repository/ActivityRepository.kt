package com.brokenbrains.fitness.data.repository

import android.util.Log
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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

    fun getAllActivities(): List<ActivityModel> {
        val activities = mutableListOf<ActivityModel>()

        CoroutineScope(Dispatchers.IO).launch {
            db.collection("users")
                .document(authRepository.currentUser!!.uid)
                .collection("activities").orderBy("startAt")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        activities.add(document.toObject(ActivityModel::class.java))
                    }
                    Log.d(this::class.simpleName, "DocumentSnapshot added with ID: $activities")
                }.addOnFailureListener { e ->
                    Log.w(this::class.simpleName, "Error getting documents $e")
                }.await()
        }
        return activities
    }
//                .snapshotFlow()
//                .map { querySnapshot ->
//                    querySnapshot.documents.map { it.toObject<ActivityModel>() }
//                }


    fun getActivityByType(type: ActivityType): List<ActivityModel> {
        return listOf()
    }


}