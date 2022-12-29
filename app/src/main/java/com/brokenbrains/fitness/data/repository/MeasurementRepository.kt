package com.brokenbrains.fitness.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.brokenbrains.fitness.data.model.measurement.MeasurementModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val authRepository: AuthRepository
) {

    fun addNewMeasurement(
        measurementModel: MeasurementModel
    ) {
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("measurements")
            .add(measurementModel).addOnSuccessListener { documentReference ->
                Log.d(
                    this::class.simpleName,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
            }.addOnFailureListener { e ->
                Log.w(this::class.simpleName, "Error adding document $e")
            }
    }

    suspend fun getMeasurementById(id: Int): Flow<MeasurementModel> {
        var measurementModel = MeasurementModel()
        return try {
            flow {
                db.collection("users")
                    .document(authRepository.currentUser!!.uid)
                    .collection("measurements")
                    .document(id.toString())
                    .get()
                    .addOnSuccessListener { document ->
                        measurementModel = document.toObject(measurementModel::class.java)!!
                    }
                emit(measurementModel)
            }
        } catch (e: Exception) {
            flow {
                emit(measurementModel)
            }
        }
    }


    @WorkerThread
    fun getAllMeasurements(): Flow<List<MeasurementModel>> = callbackFlow {
        val activities = mutableListOf<MeasurementModel>()
        db.collection("users")
            .document(authRepository.currentUser!!.uid)
            .collection("activities").orderBy("startAt")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    activities.add(document.toObject(MeasurementModel::class.java))
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


}